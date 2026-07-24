package com.grupo33.stackdemo.controllers;

import com.grupo33.stackdemo.CalculationRequest;
import com.grupo33.stackdemo.CalculationResponse;
import com.grupo33.stackdemo.service.OciService;
import com.oracle.bmc.functions.responses.InvokeFunctionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class CalculateController {
    private final OciService service;


    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        System.out.println("in /calculate");
        InvokeFunctionResponse apiResponse = service.invokeFunction(request);
        System.out.println("apiResponse: " + apiResponse);
        String body = "";
        try {
            body = new String(
                    apiResponse.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            body = e.getMessage();
        }
        double result = request.firstNumber() + request.secondNumber();
        CalculationResponse calculationResult = new CalculationResponse(request.firstNumber(), request.secondNumber(), result, body);
        return ResponseEntity.ok(calculationResult);
    }
}
