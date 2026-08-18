package com.energiai.backend.repository;

import com.energiai.backend.entity.AnalisisRegistro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisisRegistroRepository extends JpaRepository<AnalisisRegistro, Long> {
    Page<AnalisisRegistro> findByCategoria(String categoria, Pageable pageable);
}
