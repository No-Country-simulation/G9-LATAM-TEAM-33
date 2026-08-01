package com.energiai.backend.service;

import com.energiai.backend.client.ModeloEnergeticoClient;
import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import com.energiai.backend.dto.PrediccionModelo;
import com.oracle.bmc.functions.responses.InvokeFunctionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class AnalisisService {

    private final ModeloEnergeticoClient modeloEnergeticoClient;
    private final OciService service;

    public AnalisisResponse analizar(ConsumoRequest datos) {
        // Obtener la predicción completa del modelo
        InvokeFunctionResponse apiResponse = service.invokeFunction(datos);

        ObjectMapper mapper = new ObjectMapper();

        AnalisisResponse prediccion = mapper.readValue(
                apiResponse.getInputStream(),
                AnalisisResponse.class
        );
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
