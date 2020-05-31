package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.EntidadCertificadoraDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.EntidadCertificadora}.
 */
public interface EntidadCertificadoraService {

    /**
     * Save a entidadCertificadora.
     *
     * @param entidadCertificadoraDTO the entity to save.
     * @return the persisted entity.
     */
    EntidadCertificadoraDTO save(EntidadCertificadoraDTO entidadCertificadoraDTO);

    /**
     * Get all the entidadCertificadoras.
     *
     * @return the list of entities.
     */
    List<EntidadCertificadoraDTO> findAll();


    /**
     * Get the "id" entidadCertificadora.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntidadCertificadoraDTO> findOne(Long id);

    /**
     * Delete the "id" entidadCertificadora.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
