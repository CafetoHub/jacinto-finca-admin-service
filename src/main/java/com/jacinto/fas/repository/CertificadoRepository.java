package com.jacinto.fas.repository;

import com.jacinto.fas.domain.Certificado;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Certificado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
}
