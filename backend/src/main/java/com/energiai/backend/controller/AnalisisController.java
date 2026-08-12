package com.energiai.backend.controller;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import com.energiai.backend.service.AnalisisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnalisisController {

    private final AnalisisService analisisService;

    @PostMapping("/analisis-energetico")
    public ResponseEntity<AnalisisResponse> analizar(@Valid @RequestBody ConsumoRequest datos) {
        AnalisisResponse response = analisisService.analizar(datos);
<<<<<<< HEAD
        System.out.println("Response: " + response);
=======
        System.out.println("Response in post mapping: " + response);
>>>>>>> staging
        return ResponseEntity.ok(response);
    }
}
