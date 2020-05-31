package com.jacinto.fas.service.impl;

import com.jacinto.fas.service.EntidadCertificadoraService;
import com.jacinto.fas.domain.EntidadCertificadora;
import com.jacinto.fas.repository.EntidadCertificadoraRepository;
import com.jacinto.fas.service.dto.EntidadCertificadoraDTO;
import com.jacinto.fas.service.mapper.EntidadCertificadoraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EntidadCertificadora}.
 */
@Service
@Transactional
public class EntidadCertificadoraServiceImpl implements EntidadCertificadoraService {

    private final Logger log = LoggerFactory.getLogger(EntidadCertificadoraServiceImpl.class);

    private final EntidadCertificadoraRepository entidadCertificadoraRepository;

    private final EntidadCertificadoraMapper entidadCertificadoraMapper;

    public EntidadCertificadoraServiceImpl(EntidadCertificadoraRepository entidadCertificadoraRepository, EntidadCertificadoraMapper entidadCertificadoraMapper) {
        this.entidadCertificadoraRepository = entidadCertificadoraRepository;
        this.entidadCertificadoraMapper = entidadCertificadoraMapper;
    }

    /**
     * Save a entidadCertificadora.
     *
     * @param entidadCertificadoraDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntidadCertificadoraDTO save(EntidadCertificadoraDTO entidadCertificadoraDTO) {
        log.debug("Request to save EntidadCertificadora : {}", entidadCertificadoraDTO);
        EntidadCertificadora entidadCertificadora = entidadCertificadoraMapper.toEntity(entidadCertificadoraDTO);
        entidadCertificadora = entidadCertificadoraRepository.save(entidadCertificadora);
        return entidadCertificadoraMapper.toDto(entidadCertificadora);
    }

    /**
     * Get all the entidadCertificadoras.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntidadCertificadoraDTO> findAll() {
        log.debug("Request to get all EntidadCertificadoras");
        return entidadCertificadoraRepository.findAll().stream()
            .map(entidadCertificadoraMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one entidadCertificadora by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntidadCertificadoraDTO> findOne(Long id) {
        log.debug("Request to get EntidadCertificadora : {}", id);
        return entidadCertificadoraRepository.findById(id)
            .map(entidadCertificadoraMapper::toDto);
    }

    /**
     * Delete the entidadCertificadora by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntidadCertificadora : {}", id);

        entidadCertificadoraRepository.deleteById(id);
    }
}
