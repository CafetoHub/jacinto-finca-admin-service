package com.jacinto.fas.repository;

import com.jacinto.fas.domain.Cultivo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cultivo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
}
