package com.curso.nivel1_llm_directo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
/*
* record es una clase inmutable para los DTOs,generan constructores,
* getters, setters, hascode, toString, equals,
*
* */
public record ChatRequest (
    @NotBlank(message = "LA PREGUNTA NO PUEDE ESTAR VACIA")
    @Size(max = 1000, message = "LA PREGUNTA NO PUEDE SUPERAR LOS 1000 CARACTARES")
    String pregunta
    ){}
