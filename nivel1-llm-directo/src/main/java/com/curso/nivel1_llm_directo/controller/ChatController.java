package com.curso.nivel1_llm_directo.controller;

import com.curso.nivel1_llm_directo.dto.ChatRequest;
import com.curso.nivel1_llm_directo.dto.ChatResponse;
import com.curso.nivel1_llm_directo.services.LlmServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final LlmServices llmService;


    public ChatController(LlmServices llmService) {
        this.llmService = llmService;
    }
    @PostMapping("/directo")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        log.info(" Pregunta recibida para proveedor: {}", llmService.getNombreProveedor());

        long inicio = System.currentTimeMillis();
        String respuesta = llmService.chat(request.pregunta());
        long tiempoMs = System.currentTimeMillis() - inicio;

        log.info(" Respuesta generada en {} ms", tiempoMs);

        return ResponseEntity.ok(new ChatResponse(
                respuesta,
                llmService.getNombreProveedor(),
                llmService.getModelo(),
                tiempoMs
        ));
    }


    @GetMapping("/info")
    public ResponseEntity<ChatResponse> info() {
        return ResponseEntity.ok(new ChatResponse(
                "Proveedor activo y listo para recibir preguntas.",
                llmService.getNombreProveedor(),
                llmService.getModelo(),
                0
        ));
    }
}
