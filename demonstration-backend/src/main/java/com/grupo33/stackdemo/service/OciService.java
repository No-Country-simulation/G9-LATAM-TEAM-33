package com.grupo33.stackdemo.service;

import com.grupo33.stackdemo.CalculationRequest;
import com.oracle.bmc.functions.FunctionsInvokeClient;
import com.oracle.bmc.functions.requests.InvokeFunctionRequest;
import com.oracle.bmc.functions.responses.InvokeFunctionResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class OciService {
    private final FunctionsInvokeClient client;
    private final ObjectMapper objectMapper;

    @Value("${oci.functions.id}")
    private String functionId;

    public InvokeFunctionResponse invokeFunction(CalculationRequest request){
        String json = objectMapper.writeValueAsString(request);
        InvokeFunctionRequest functionRequest = InvokeFunctionRequest.builder().functionId(functionId).invokeFunctionBody(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))).build();

        return client.invokeFunction(functionRequest);
    }
}
