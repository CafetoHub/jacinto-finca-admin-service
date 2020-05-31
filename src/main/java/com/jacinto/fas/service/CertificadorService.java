package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.CertificadorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.Certificador}.
 */
public interface CertificadorService {

    /**
     * Save a certificador.
     *
     * @param certificadorDTO the entity to save.
     * @return the persisted entity.
     */
    CertificadorDTO save(CertificadorDTO certificadorDTO);

    /**
     * Get all the certificadors.
     *
     * @return the list of entities.
     */
    List<CertificadorDTO> findAll();


    /**
     * Get the "id" certificador.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CertificadorDTO> findOne(Long id);

    /**
     * Delete the "id" certificador.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
