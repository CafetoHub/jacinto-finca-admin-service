package com.jacinto.fas.repository;

import com.jacinto.fas.domain.EntidadCertificadora;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EntidadCertificadora entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntidadCertificadoraRepository extends JpaRepository<EntidadCertificadora, Long> {
}
