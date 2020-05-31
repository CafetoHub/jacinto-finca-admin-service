package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.AgronomoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.Agronomo}.
 */
public interface AgronomoService {

    /**
     * Save a agronomo.
     *
     * @param agronomoDTO the entity to save.
     * @return the persisted entity.
     */
    AgronomoDTO save(AgronomoDTO agronomoDTO);

    /**
     * Get all the agronomos.
     *
     * @return the list of entities.
     */
    List<AgronomoDTO> findAll();


    /**
     * Get the "id" agronomo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AgronomoDTO> findOne(Long id);

    /**
     * Delete the "id" agronomo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
