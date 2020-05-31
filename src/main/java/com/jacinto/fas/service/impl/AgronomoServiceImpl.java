package com.jacinto.fas.service.impl;

import com.jacinto.fas.service.AgronomoService;
import com.jacinto.fas.domain.Agronomo;
import com.jacinto.fas.repository.AgronomoRepository;
import com.jacinto.fas.service.dto.AgronomoDTO;
import com.jacinto.fas.service.mapper.AgronomoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Agronomo}.
 */
@Service
@Transactional
public class AgronomoServiceImpl implements AgronomoService {

    private final Logger log = LoggerFactory.getLogger(AgronomoServiceImpl.class);

    private final AgronomoRepository agronomoRepository;

    private final AgronomoMapper agronomoMapper;

    public AgronomoServiceImpl(AgronomoRepository agronomoRepository, AgronomoMapper agronomoMapper) {
        this.agronomoRepository = agronomoRepository;
        this.agronomoMapper = agronomoMapper;
    }

    /**
     * Save a agronomo.
     *
     * @param agronomoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AgronomoDTO save(AgronomoDTO agronomoDTO) {
        log.debug("Request to save Agronomo : {}", agronomoDTO);
        Agronomo agronomo = agronomoMapper.toEntity(agronomoDTO);
        agronomo = agronomoRepository.save(agronomo);
        return agronomoMapper.toDto(agronomo);
    }

    /**
     * Get all the agronomos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgronomoDTO> findAll() {
        log.debug("Request to get all Agronomos");
        return agronomoRepository.findAll().stream()
            .map(agronomoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one agronomo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AgronomoDTO> findOne(Long id) {
        log.debug("Request to get Agronomo : {}", id);
        return agronomoRepository.findById(id)
            .map(agronomoMapper::toDto);
    }

    /**
     * Delete the agronomo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agronomo : {}", id);

        agronomoRepository.deleteById(id);
    }
}
