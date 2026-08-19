package com.energiai.backend.client;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;
import com.energiai.backend.service.PythonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("oci")
@RequiredArgsConstructor
public class ModeloOciClient implements ModeloEnergeticoClient {

    private final PythonService pythonService;

    @Override
    public PrediccionModelo predecir(ConsumoRequest datos) {
        return pythonService.predecir(datos);
    }
}
