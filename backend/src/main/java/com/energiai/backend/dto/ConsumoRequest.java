package com.energiai.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumoRequest {

    @NotNull(message = "El consumo en Kwh es obligatorio")
    @Positive(message = "El consumo en Kwh debe ser positivo")
    private Double consumoKwh;

    @NotNull(message = "El uso en horario pico es obligatorio")
    private Boolean usoHorarioPico;

    @NotNull(message = "Las horas de alto consumo son obligatorias")
    @Positive(message = "Las horas de alto consumo deben ser un número positivo")
    private Integer horasAltoConsumo;

    @NotNull(message = "La cantidad de equipos es obligatoria")
    @Positive(message = "La cantidad de equipos debe ser un número positivo")
    private Integer cantidadEquipos;

    @NotNull(message = "La cantidad de personas es obligatoria")
    @Positive(message = "La cantidad de personas debe ser un número positivo")
    private Integer cantidadPersonas;

    @NotBlank(message = "El tipo de inmueble es obligatorio")
    private String tipoInmueble;

    @Min(value = 1, message = "El mes debe ser entre 1 y 12")
    @Max(value = 12, message = "El mes debe ser entre 1 y 12")
    private Integer mes;
}
