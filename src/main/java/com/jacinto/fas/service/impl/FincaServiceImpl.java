package com.jacinto.fas.service.impl;

import com.jacinto.fas.service.FincaService;
import com.jacinto.fas.domain.Finca;
import com.jacinto.fas.repository.FincaRepository;
import com.jacinto.fas.service.dto.FincaDTO;
import com.jacinto.fas.service.mapper.FincaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Finca}.
 */
@Service
@Transactional
public class FincaServiceImpl implements FincaService {

    private final Logger log = LoggerFactory.getLogger(FincaServiceImpl.class);

    private final FincaRepository fincaRepository;

    private final FincaMapper fincaMapper;

    public FincaServiceImpl(FincaRepository fincaRepository, FincaMapper fincaMapper) {
        this.fincaRepository = fincaRepository;
        this.fincaMapper = fincaMapper;
    }

    /**
     * Save a finca.
     *
     * @param fincaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FincaDTO save(FincaDTO fincaDTO) {
        log.debug("Request to save Finca : {}", fincaDTO);
        Finca finca = fincaMapper.toEntity(fincaDTO);
        finca = fincaRepository.save(finca);
        return fincaMapper.toDto(finca);
    }

    /**
     * Get all the fincas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FincaDTO> findAll() {
        log.debug("Request to get all Fincas");
        return fincaRepository.findAll().stream()
            .map(fincaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one finca by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FincaDTO> findOne(Long id) {
        log.debug("Request to get Finca : {}", id);
        return fincaRepository.findById(id)
            .map(fincaMapper::toDto);
    }

    /**
     * Delete the finca by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Finca : {}", id);

        fincaRepository.deleteById(id);
    }
}
