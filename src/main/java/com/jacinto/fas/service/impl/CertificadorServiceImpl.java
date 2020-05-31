package com.jacinto.fas.service.impl;

import com.jacinto.fas.service.CertificadorService;
import com.jacinto.fas.domain.Certificador;
import com.jacinto.fas.repository.CertificadorRepository;
import com.jacinto.fas.service.dto.CertificadorDTO;
import com.jacinto.fas.service.mapper.CertificadorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Certificador}.
 */
@Service
@Transactional
public class CertificadorServiceImpl implements CertificadorService {

    private final Logger log = LoggerFactory.getLogger(CertificadorServiceImpl.class);

    private final CertificadorRepository certificadorRepository;

    private final CertificadorMapper certificadorMapper;

    public CertificadorServiceImpl(CertificadorRepository certificadorRepository, CertificadorMapper certificadorMapper) {
        this.certificadorRepository = certificadorRepository;
        this.certificadorMapper = certificadorMapper;
    }

    /**
     * Save a certificador.
     *
     * @param certificadorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CertificadorDTO save(CertificadorDTO certificadorDTO) {
        log.debug("Request to save Certificador : {}", certificadorDTO);
        Certificador certificador = certificadorMapper.toEntity(certificadorDTO);
        certificador = certificadorRepository.save(certificador);
        return certificadorMapper.toDto(certificador);
    }

    /**
     * Get all the certificadors.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CertificadorDTO> findAll() {
        log.debug("Request to get all Certificadors");
        return certificadorRepository.findAll().stream()
            .map(certificadorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one certificador by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CertificadorDTO> findOne(Long id) {
        log.debug("Request to get Certificador : {}", id);
        return certificadorRepository.findById(id)
            .map(certificadorMapper::toDto);
    }

    /**
     * Delete the certificador by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Certificador : {}", id);

        certificadorRepository.deleteById(id);
    }
}
