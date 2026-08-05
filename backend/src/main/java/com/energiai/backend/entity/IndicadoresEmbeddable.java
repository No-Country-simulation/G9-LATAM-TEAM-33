package com.energiai.backend.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadoresEmbeddable {
    private double consumoPorEquipo;
    private double consumoPorPersona;
    private double consumoPorHora;
}
