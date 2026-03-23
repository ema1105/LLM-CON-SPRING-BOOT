package com.curso.nivel1_llm_directo.services;

public interface LlmServices {
    String chat(String pregunta);
    String getNombreProveedor();
    String getModelo();
}
