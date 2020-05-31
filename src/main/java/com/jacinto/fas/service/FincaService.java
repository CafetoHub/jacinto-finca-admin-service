package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.FincaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.Finca}.
 */
public interface FincaService {

    /**
     * Save a finca.
     *
     * @param fincaDTO the entity to save.
     * @return the persisted entity.
     */
    FincaDTO save(FincaDTO fincaDTO);

    /**
     * Get all the fincas.
     *
     * @return the list of entities.
     */
    List<FincaDTO> findAll();


    /**
     * Get the "id" finca.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FincaDTO> findOne(Long id);

    /**
     * Delete the "id" finca.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
