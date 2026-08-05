package com.energiai.backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "analisis_registro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalisisRegistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private double consumoKwh;
    private boolean usoHorarioPico;
    private int horasAltoConsumo;
    private int cantidadEquipos;
    private int cantidadPersonas;
    private String tipoInmueble;
    private Integer mes;
    private String categoria;
    private double probabilidad;
    private double costoEstimadoMensual;

    @Embedded
    private IndicadoresEmbeddable indicadores;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "analisis_id")
    private List<RecomendacionRegistro> recomendaciones;
}
