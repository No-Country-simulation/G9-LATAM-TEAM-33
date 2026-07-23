package com.energiai.backend.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalisisResponse {

    private String categoria;
    private double probabilidad;
    private List<String> recomendaciones;
    private double costoEstimadoMensual;
}
