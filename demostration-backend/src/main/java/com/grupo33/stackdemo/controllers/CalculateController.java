package com.grupo33.stackdemo.controllers;

import com.grupo33.stackdemo.CalculationRequest;
import com.grupo33.stackdemo.CalculationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculateController {
    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        double result = request.firstNumber() + request.secondNumber();
        CalculationResponse calculationResult = new CalculationResponse(request.firstNumber(), request.secondNumber(), result);
        return ResponseEntity.ok(calculationResult);
    }
}
