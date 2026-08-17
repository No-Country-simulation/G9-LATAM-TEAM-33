package com.energiai.backend.controller;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import com.energiai.backend.service.AnalisisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnalisisController {

    private final AnalisisService analisisService;

    @PostMapping("/{version}/analisis-energetico")
    public ResponseEntity<?> analizar(@PathVariable String version, @Valid @RequestBody ConsumoRequest datos) {
        String modelo;
        if ("v1".equals(version)) {
            modelo = "lr";
        } else if ("v2".equals(version)) {
            modelo = "rf";
        } else {
            return ResponseEntity.badRequest().body("Version invalida, use v1 o v2");
        }

        datos.setModelo(modelo);
        AnalisisResponse response = analisisService.analizar(datos);
        System.out.println("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
