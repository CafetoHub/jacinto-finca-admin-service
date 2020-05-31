package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.LoteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.Lote}.
 */
public interface LoteService {

    /**
     * Save a lote.
     *
     * @param loteDTO the entity to save.
     * @return the persisted entity.
     */
    LoteDTO save(LoteDTO loteDTO);

    /**
     * Get all the lotes.
     *
     * @return the list of entities.
     */
    List<LoteDTO> findAll();


    /**
     * Get the "id" lote.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoteDTO> findOne(Long id);

    /**
     * Delete the "id" lote.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
