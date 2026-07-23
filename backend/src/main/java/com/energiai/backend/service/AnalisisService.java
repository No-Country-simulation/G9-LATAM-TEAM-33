package com.energiai.backend.service;

import com.energiai.backend.client.ModeloEnergeticoClient;
import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import com.energiai.backend.dto.PrediccionModelo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalisisService {

    private final ModeloEnergeticoClient modeloEnergeticoClient;

    public AnalisisResponse analizar(ConsumoRequest datos) {
        // Calcular costoEstimadoMensual de forma real: consumoKwh * 0.75
        double costoEstimadoMensual = datos.getConsumoKwh() * 0.75;

        // Obtener la predicción del modelo (simulado)
        PrediccionModelo prediccion = modeloEnergeticoClient.predecir(datos);

        List<String> recomendaciones = List.of(
            "Reducir el consumo de equipos de alto consumo durante el horario pico.",
            "Distribuir el uso de electrodomésticos de manera uniforme a lo largo del día.",
            "Desconectar los equipos que no se encuentren en uso para evitar consumo vampiro."
        );

        return AnalisisResponse.builder()
                .categoria(prediccion.getCategoria())
                .probabilidad(prediccion.getProbabilidad())
                .recomendaciones(recomendaciones)
                .costoEstimadoMensual(costoEstimadoMensual)
                .build();
    }
}
