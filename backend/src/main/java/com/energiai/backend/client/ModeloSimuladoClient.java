package com.energiai.backend.client;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;
import org.springframework.stereotype.Component;

@Component
public class ModeloSimuladoClient implements ModeloEnergeticoClient {

    @Override
    public PrediccionModelo predecir(ConsumoRequest datos) {
        return PrediccionModelo.builder()
                .categoria("Moderado")
                .probabilidad(0.75)
                .build();
    }
}
