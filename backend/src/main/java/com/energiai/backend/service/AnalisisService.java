package com.energiai.backend.service;

import com.energiai.backend.client.ModeloEnergeticoClient;
import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.AnalisisResponse;
import com.energiai.backend.dto.PrediccionModelo;
import com.energiai.backend.entity.AnalisisRegistro;
import com.energiai.backend.entity.IndicadoresEmbeddable;
import com.energiai.backend.entity.RecomendacionRegistro;
import com.energiai.backend.repository.AnalisisRegistroRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalisisService {

    private final ModeloEnergeticoClient modeloEnergeticoClient;
    private final AnalisisRegistroRepository analisisRegistroRepository;

    public AnalisisResponse analizar(ConsumoRequest datos) {
        PrediccionModelo prediccion = modeloEnergeticoClient.predecir(datos);

        AnalisisResponse response = AnalisisResponse.builder()
                .categoria(prediccion.getCategoria())
                .probabilidad(prediccion.getProbabilidad())
                .recomendaciones(prediccion.getRecomendaciones())
                .costoEstimadoMensual(prediccion.getCostoEstimadoMensual())
                .indicadores(prediccion.getIndicadores())
                .build();

        try {
            AnalisisRegistro registro = construirRegistro(datos, response);
            analisisRegistroRepository.save(registro);
        } catch (Exception e) {
            System.err.println("[AnalisisService] Error al guardar el registro en la base de datos: " + e.getMessage());
        }

        return response;
    }

    private AnalisisRegistro construirRegistro(ConsumoRequest datos, AnalisisResponse respuesta) {
        var indicadoresResponse = respuesta.getIndicadores();
        IndicadoresEmbeddable indicadoresEmbeddable = null;
        if (indicadoresResponse != null) {
            indicadoresEmbeddable = IndicadoresEmbeddable.builder()
                    .consumoPorEquipo(indicadoresResponse.getConsumoPorEquipo())
                    .consumoPorPersona(indicadoresResponse.getConsumoPorPersona())
                    .consumoPorHora(indicadoresResponse.getConsumoPorHora())
                    .build();
        }

        var recomendacionesResponse = respuesta.getRecomendaciones();
        java.util.List<RecomendacionRegistro> recomendacionesRegistro = null;
        if (recomendacionesResponse != null) {
            recomendacionesRegistro = recomendacionesResponse.stream()
                    .map(rec -> RecomendacionRegistro.builder()
                            .prioridad(rec.getPrioridad())
                            .impacto(rec.getImpacto())
                            .mensaje(rec.getMensaje())
                            .build())
                    .collect(Collectors.toList());
        }

        return AnalisisRegistro.builder()
                .consumoKwh(datos.getConsumoKwh())
                .usoHorarioPico(datos.getUsoHorarioPico())
                .horasAltoConsumo(datos.getHorasAltoConsumo())
                .cantidadEquipos(datos.getCantidadEquipos())
                .cantidadPersonas(datos.getCantidadPersonas())
                .tipoInmueble(datos.getTipoInmueble())
                .mes(datos.getMes())
                .categoria(respuesta.getCategoria())
                .probabilidad(respuesta.getProbabilidad())
                .costoEstimadoMensual(respuesta.getCostoEstimadoMensual())
                .indicadores(indicadoresEmbeddable)
                .recomendaciones(recomendacionesRegistro)
                .build();
    }
}
