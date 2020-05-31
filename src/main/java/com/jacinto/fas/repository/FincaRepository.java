package com.jacinto.fas.repository;

import com.jacinto.fas.domain.Finca;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Finca entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FincaRepository extends JpaRepository<Finca, Long> {
}
