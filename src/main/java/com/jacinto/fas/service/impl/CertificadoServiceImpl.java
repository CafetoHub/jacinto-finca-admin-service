package com.jacinto.fas.service.impl;

import com.jacinto.fas.service.CertificadoService;
import com.jacinto.fas.domain.Certificado;
import com.jacinto.fas.repository.CertificadoRepository;
import com.jacinto.fas.service.dto.CertificadoDTO;
import com.jacinto.fas.service.mapper.CertificadoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Certificado}.
 */
@Service
@Transactional
public class CertificadoServiceImpl implements CertificadoService {

    private final Logger log = LoggerFactory.getLogger(CertificadoServiceImpl.class);

    private final CertificadoRepository certificadoRepository;

    private final CertificadoMapper certificadoMapper;

    public CertificadoServiceImpl(CertificadoRepository certificadoRepository, CertificadoMapper certificadoMapper) {
        this.certificadoRepository = certificadoRepository;
        this.certificadoMapper = certificadoMapper;
    }

    /**
     * Save a certificado.
     *
     * @param certificadoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CertificadoDTO save(CertificadoDTO certificadoDTO) {
        log.debug("Request to save Certificado : {}", certificadoDTO);
        Certificado certificado = certificadoMapper.toEntity(certificadoDTO);
        certificado = certificadoRepository.save(certificado);
        return certificadoMapper.toDto(certificado);
    }

    /**
     * Get all the certificados.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CertificadoDTO> findAll() {
        log.debug("Request to get all Certificados");
        return certificadoRepository.findAll().stream()
            .map(certificadoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one certificado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CertificadoDTO> findOne(Long id) {
        log.debug("Request to get Certificado : {}", id);
        return certificadoRepository.findById(id)
            .map(certificadoMapper::toDto);
    }

    /**
     * Delete the certificado by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Certificado : {}", id);

        certificadoRepository.deleteById(id);
    }
}
