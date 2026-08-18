package com.energiai.backend.service;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class PythonService {
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public PrediccionModelo predecir(ConsumoRequest request){

        try {
            System.out.println("Request en invokeFunction: " + request.toString());

//            String json = objectMapper.writeValueAsString(request);
//            System.out.println("JSON de Request: " + json);
            String json = objectMapper.writeValueAsString(request);
            System.out.println("Invoking FastAPI...");

            String response = restClient.post()
                    .uri("/predict")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json)
                    .retrieve()
                    .body(String.class);

            System.out.println("Respuesta FastAPI: " + response);

            return objectMapper.readValue(response, PrediccionModelo.class);
        } catch (RestClientException e){
            System.out.println("Error calling FastAPI: " + e.getMessage());
            throw e;
        }
    }
}
