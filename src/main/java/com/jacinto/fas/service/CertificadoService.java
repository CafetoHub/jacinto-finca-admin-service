package com.jacinto.fas.service;

import com.jacinto.fas.service.dto.CertificadoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jacinto.fas.domain.Certificado}.
 */
public interface CertificadoService {

    /**
     * Save a certificado.
     *
     * @param certificadoDTO the entity to save.
     * @return the persisted entity.
     */
    CertificadoDTO save(CertificadoDTO certificadoDTO);

    /**
     * Get all the certificados.
     *
     * @return the list of entities.
     */
    List<CertificadoDTO> findAll();


    /**
     * Get the "id" certificado.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CertificadoDTO> findOne(Long id);

    /**
     * Delete the "id" certificado.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
