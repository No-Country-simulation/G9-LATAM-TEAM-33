package com.energiai.backend.service;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;
import lombok.RequiredArgsConstructor;
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

    public PrediccionModelo predecir(ConsumoRequest request) {
        try {
            String json = objectMapper.writeValueAsString(request);
            String response = restClient.post()
                    .uri("/predict")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json)
                    .retrieve()
                    .body(String.class);
            return objectMapper.readValue(response, PrediccionModelo.class);
        } catch (RestClientException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
