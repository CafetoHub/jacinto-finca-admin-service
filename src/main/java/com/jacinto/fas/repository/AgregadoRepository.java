package com.jacinto.fas.repository;

import com.jacinto.fas.domain.Agregado;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Agregado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgregadoRepository extends JpaRepository<Agregado, Long> {
}
