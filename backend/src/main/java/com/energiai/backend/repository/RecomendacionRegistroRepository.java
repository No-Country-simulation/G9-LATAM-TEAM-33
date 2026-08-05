package com.energiai.backend.repository;

import com.energiai.backend.entity.RecomendacionRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacionRegistroRepository extends JpaRepository<RecomendacionRegistro, Long> {
}
