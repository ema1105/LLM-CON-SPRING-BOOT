package com.curso.nivel1_llm_directo.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import java.util.List;
import java.util.Map;
import org.slf4j.Logger;

@Service
@ConditionalOnProperty(name = "llm.provider", havingValue = "gemini")
public class GeminiDirectServices implements LlmServices{

    private static final Logger log = LoggerFactory.getLogger(GeminiDirectServices.class);

    private final WebClient webClient;
    private final String model;

    public GeminiDirectServices(
            @Value("${gemini.api.key}") String apikey,
            @Value("${gemini.base-url}") String baseUrl,
            @Value("${gemini.model}") String model) {

        this.model = model;

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apikey)
                .defaultHeader("Content-Type", "application/json")
                .build();

        log.info("GeminiDirectServices inicializado | modelo: {} | baseUrl: {} ", model, baseUrl);
    }

    @Override
    public String chat(String pregunta){
        log.debug("-> enviando pregunta a gemini : '{}'", pregunta);
        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system",
                                "content", "Eres un asistente útil y didáctico. "
                                        + "Responde siempre en español, de forma clara y concisa."),
                        Map.of("role", "user", "content", pregunta)
                )
        );

        try {
            String respuesta = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(resp -> {
                        // Navegar la respuesta: choices[0].message.content
                        @SuppressWarnings("unchecked")
                        var choices = (List<Map<String, Object>>) resp.get("choices");
                        @SuppressWarnings("unchecked")
                        var message = (Map<String, Object>) choices.get(0).get("message");
                        return (String) message.get("content");
                    })
                    .block();

            log.debug("Respuesta recibida de Gemini ({} chars)",
                    respuesta != null ? respuesta.length() : 0);
            return respuesta;

        } catch (WebClientResponseException e) {
            log.error(" Error HTTP {} desde Gemini: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException(
                    "Error al llamar a Gemini: " + e.getStatusCode()
                            + " — Verifica que tu GEMINI_API_KEY es válida.", e);
        }
    }

    @Override public String getNombreProveedor() { return "Google AI Studio (Gemini)"; }
    @Override public String getModelo()           { return model; }
}

