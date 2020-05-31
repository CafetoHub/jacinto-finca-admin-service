package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.AgregadoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.Agregado}.
 */
public interface AgregadoService {

    /**
     * Save a agregado.
     *
     * @param agregadoDTO the entity to save.
     * @return the persisted entity.
     */
    AgregadoDTO save(AgregadoDTO agregadoDTO);

    /**
     * Get all the agregados.
     *
     * @return the list of entities.
     */
    List<AgregadoDTO> findAll();


    /**
     * Get the "id" agregado.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AgregadoDTO> findOne(Long id);

    /**
     * Delete the "id" agregado.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
