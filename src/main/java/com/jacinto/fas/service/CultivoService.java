package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.CultivoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.Cultivo}.
 */
public interface CultivoService {

    /**
     * Save a cultivo.
     *
     * @param cultivoDTO the entity to save.
     * @return the persisted entity.
     */
    CultivoDTO save(CultivoDTO cultivoDTO);

    /**
     * Get all the cultivos.
     *
     * @return the list of entities.
     */
    List<CultivoDTO> findAll();


    /**
     * Get the "id" cultivo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CultivoDTO> findOne(Long id);

    /**
     * Delete the "id" cultivo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
