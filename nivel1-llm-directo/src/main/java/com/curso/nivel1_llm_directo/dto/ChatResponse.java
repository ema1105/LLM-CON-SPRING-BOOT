package com.curso.nivel1_llm_directo.dto;

public record ChatResponse (
        String respuesta,
        String proveedor,
        String modelo,
        long tiempoMs

) {}
