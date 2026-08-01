package com.energiai.backend.service;

import com.energiai.backend.client.ModeloEnergeticoClient;
import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import com.energiai.backend.dto.PrediccionModelo;
import com.oracle.bmc.functions.responses.InvokeFunctionResponse;
import com.oracle.bmc.model.BmcException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class AnalisisService {

    private final ModeloEnergeticoClient modeloEnergeticoClient;
    private final OciService service;

    public AnalisisResponse analizar(ConsumoRequest datos) throws IOException {
        System.out.println("Iniciando analisis");
        System.out.println("Datos: " + datos);
        // Obtener la predicción completa del modelo
        InvokeFunctionResponse apiResponse = service.invokeFunction(datos);
        System.out.println("Response: " + apiResponse);

        ObjectMapper mapper = new ObjectMapper();

        String json = new String(
                apiResponse.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        System.out.println("json respuesta de python: " + json);

        AnalisisResponse prediccion = mapper.readValue(
                json,
                AnalisisResponse.class
        );
        System.out.println("Prediction: " + prediccion);
//        PrediccionModelo prediccion = modeloEnergeticoClient.predecir(datos);

        // Copiar directamente los campos recibidos
        return AnalisisResponse.builder()
                .categoria(prediccion.getCategoria())
                .probabilidad(prediccion.getProbabilidad())
                .recomendaciones(prediccion.getRecomendaciones())
                .costoEstimadoMensual(prediccion.getCostoEstimadoMensual())
                .indicadores(prediccion.getIndicadores())
                .build();
    }
}
