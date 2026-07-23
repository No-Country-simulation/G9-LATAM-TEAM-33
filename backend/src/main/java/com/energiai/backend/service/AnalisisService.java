package com.energiai.backend.service;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnalisisService {

    public AnalisisResponse analizar(ConsumoRequest datos) {
        // Calcular costoEstimadoMensual de forma real: consumoKwh * 0.75
        double costoEstimadoMensual = datos.getConsumoKwh() * 0.75;

        // TODO: reemplazar por la predicción real de ModeloEnergeticoClient cuando esté implementado
        String categoria = "Moderado";
        double probabilidad = 0.75;

        List<String> recomendaciones = List.of(
            "Reducir el consumo de equipos de alto consumo durante el horario pico.",
            "Distribuir el uso de electrodomésticos de manera uniforme a lo largo del día.",
            "Desconectar los equipos que no se encuentren en uso para evitar consumo vampiro."
        );

        return AnalisisResponse.builder()
                .categoria(categoria)
                .probabilidad(probabilidad)
                .recomendaciones(recomendaciones)
                .costoEstimadoMensual(costoEstimadoMensual)
                .build();
    }
}
