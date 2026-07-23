package com.energiai.backend.client;

import com.energiai.backend.dto.ConsumoRequest;
import com.energiai.backend.dto.PrediccionModelo;

public interface ModeloEnergeticoClient {
    PrediccionModelo predecir(ConsumoRequest datos);
}
