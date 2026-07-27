package com.grupo33.stackdemo.controllers;

import com.grupo33.stackdemo.CalculationRequest;
import com.grupo33.stackdemo.CalculationResponse;
import com.grupo33.stackdemo.service.OciService;
import com.oracle.bmc.functions.responses.InvokeFunctionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class CalculateController {
    private final OciService service;

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        //llamo a la Function directo, pasandole el request body
        try {
            InvokeFunctionResponse apiResponse = service.invokeFunction(request);

            ObjectMapper mapper = new ObjectMapper();

            CalculationResponse body = mapper.readValue(
                    apiResponse.getInputStream(),
                    CalculationResponse.class
            );

            return ResponseEntity.ok(body);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
