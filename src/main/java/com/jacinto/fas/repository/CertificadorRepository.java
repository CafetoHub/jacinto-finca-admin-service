package com.jacinto.fas.repository;

import com.jacinto.fas.domain.Certificador;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Certificador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificadorRepository extends JpaRepository<Certificador, Long> {
}
