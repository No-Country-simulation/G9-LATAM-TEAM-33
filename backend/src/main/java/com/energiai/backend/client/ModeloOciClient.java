package com.energiai.backend.client;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;
import com.energiai.backend.service.OciService;
import com.oracle.bmc.functions.responses.InvokeFunctionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class ModeloOciClient implements ModeloEnergeticoClient {

    private final OciService ociService;
    private final ObjectMapper objectMapper;

    @Override
    public PrediccionModelo predecir(ConsumoRequest datos) {
        InvokeFunctionResponse apiResponse = ociService.invokeFunction(datos);

        try {
            byte[] responseBytes = apiResponse.getInputStream().readAllBytes();
            String responseJson = new String(responseBytes, StandardCharsets.UTF_8);
            return objectMapper.readValue(responseJson, PrediccionModelo.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize OCI Function response", e);
        }
    }
}
