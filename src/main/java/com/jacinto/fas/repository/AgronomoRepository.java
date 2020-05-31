package com.jacinto.fas.repository;

import com.jacinto.fas.domain.Agronomo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Agronomo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgronomoRepository extends JpaRepository<Agronomo, Long> {
}
