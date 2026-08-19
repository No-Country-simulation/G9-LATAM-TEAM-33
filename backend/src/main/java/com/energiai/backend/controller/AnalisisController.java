package com.energiai.backend.controller;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import com.energiai.backend.service.AnalisisService;
import com.energiai.backend.entity.AnalisisRegistro;
import com.energiai.backend.repository.AnalisisRegistroRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnalisisController {

    private final AnalisisService analisisService;
    private final AnalisisRegistroRepository analisisRegistroRepository;

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

    @GetMapping("/historial")
    public ResponseEntity<Page<AnalisisRegistro>> historial(
        @RequestParam(required = false) String categoria,
        @PageableDefault(size = 10, sort = "fechaHora", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<AnalisisRegistro> resultado;
        if (categoria != null && !categoria.trim().isEmpty()) {
            resultado = analisisRegistroRepository.findByCategoria(categoria, pageable);
        } else {
            resultado = analisisRegistroRepository.findAll(pageable);
        }
        return ResponseEntity.ok(resultado);
    }
}
