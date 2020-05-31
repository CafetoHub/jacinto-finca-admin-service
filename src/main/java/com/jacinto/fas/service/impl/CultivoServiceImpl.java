package com.jacinto.fas.service.impl;

import com.jacinto.fas.service.CultivoService;
import com.jacinto.fas.domain.Cultivo;
import com.jacinto.fas.repository.CultivoRepository;
import com.jacinto.fas.service.dto.CultivoDTO;
import com.jacinto.fas.service.mapper.CultivoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Cultivo}.
 */
@Service
@Transactional
public class CultivoServiceImpl implements CultivoService {

    private final Logger log = LoggerFactory.getLogger(CultivoServiceImpl.class);

    private final CultivoRepository cultivoRepository;

    private final CultivoMapper cultivoMapper;

    public CultivoServiceImpl(CultivoRepository cultivoRepository, CultivoMapper cultivoMapper) {
        this.cultivoRepository = cultivoRepository;
        this.cultivoMapper = cultivoMapper;
    }

    /**
     * Save a cultivo.
     *
     * @param cultivoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CultivoDTO save(CultivoDTO cultivoDTO) {
        log.debug("Request to save Cultivo : {}", cultivoDTO);
        Cultivo cultivo = cultivoMapper.toEntity(cultivoDTO);
        cultivo = cultivoRepository.save(cultivo);
        return cultivoMapper.toDto(cultivo);
    }

    /**
     * Get all the cultivos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CultivoDTO> findAll() {
        log.debug("Request to get all Cultivos");
        return cultivoRepository.findAll().stream()
            .map(cultivoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one cultivo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CultivoDTO> findOne(Long id) {
        log.debug("Request to get Cultivo : {}", id);
        return cultivoRepository.findById(id)
            .map(cultivoMapper::toDto);
    }

    /**
     * Delete the cultivo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cultivo : {}", id);

        cultivoRepository.deleteById(id);
    }
}
