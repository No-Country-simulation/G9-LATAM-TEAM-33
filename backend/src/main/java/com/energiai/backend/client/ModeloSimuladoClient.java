package com.energiai.backend.client;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ModeloSimuladoClient implements ModeloEnergeticoClient {

    @Override
    public PrediccionModelo predecir(ConsumoRequest datos) {
        double costoEstimadoMensual = datos.getConsumoKwh() * 0.75;
        List<String> recomendaciones = List.of(
            "Reducir el consumo de equipos de alto consumo durante el horario pico.",
            "Distribuir el uso de electrodomésticos de manera uniforme a lo largo del día.",
            "Desconectar los equipos que no se encuentren en uso para evitar consumo vampiro."
        );

        return PrediccionModelo.builder()
                .categoria("Moderado")
                .probabilidad(0.75)
                .recomendaciones(recomendaciones)
                .costoEstimadoMensual(costoEstimadoMensual)
                .build();
    }
}
