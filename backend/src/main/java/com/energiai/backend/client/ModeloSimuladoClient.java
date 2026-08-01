package com.energiai.backend.client;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;
import com.energiai.backend.dto.Indicadores;
import com.energiai.backend.dto.Recomendacion;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Profile("!oci")
public class ModeloSimuladoClient implements ModeloEnergeticoClient {

    @Override
    public PrediccionModelo predecir(ConsumoRequest datos) {
        double costoEstimadoMensual = datos.getConsumoKwh() * 0.75;
        
        Indicadores indicadores = Indicadores.builder()
                .consumoPorEquipo(12.5)
                .consumoPorPersona(30.0)
                .consumoPorHora(1.8)
                .build();

        List<Recomendacion> recomendaciones = List.of(
            Recomendacion.builder()
                .prioridad("Alta")
                .impacto("Alto")
                .mensaje("Reducir el consumo de equipos de alto consumo durante el horario pico.")
                .build(),
            Recomendacion.builder()
                .prioridad("Media")
                .impacto("Moderado")
                .mensaje("Distribuir el uso de electrodomésticos de manera uniforme a lo largo del día.")
                .build()
        );

        return PrediccionModelo.builder()
                .categoria("Moderado")
                .probabilidad(0.75)
                .recomendaciones(recomendaciones)
                .costoEstimadoMensual(costoEstimadoMensual)
                .indicadores(indicadores)
                .build();
    }
}
