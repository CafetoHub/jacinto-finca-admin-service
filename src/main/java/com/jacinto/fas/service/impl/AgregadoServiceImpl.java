package com.jacinto.fas.service.impl;

import com.jacinto.fas.service.AgregadoService;
import com.jacinto.fas.domain.Agregado;
import com.jacinto.fas.repository.AgregadoRepository;
import com.jacinto.fas.service.dto.AgregadoDTO;
import com.jacinto.fas.service.mapper.AgregadoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Agregado}.
 */
@Service
@Transactional
public class AgregadoServiceImpl implements AgregadoService {

    private final Logger log = LoggerFactory.getLogger(AgregadoServiceImpl.class);

    private final AgregadoRepository agregadoRepository;

    private final AgregadoMapper agregadoMapper;

    public AgregadoServiceImpl(AgregadoRepository agregadoRepository, AgregadoMapper agregadoMapper) {
        this.agregadoRepository = agregadoRepository;
        this.agregadoMapper = agregadoMapper;
    }

    /**
     * Save a agregado.
     *
     * @param agregadoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AgregadoDTO save(AgregadoDTO agregadoDTO) {
        log.debug("Request to save Agregado : {}", agregadoDTO);
        Agregado agregado = agregadoMapper.toEntity(agregadoDTO);
        agregado = agregadoRepository.save(agregado);
        return agregadoMapper.toDto(agregado);
    }

    /**
     * Get all the agregados.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgregadoDTO> findAll() {
        log.debug("Request to get all Agregados");
        return agregadoRepository.findAll().stream()
            .map(agregadoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one agregado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AgregadoDTO> findOne(Long id) {
        log.debug("Request to get Agregado : {}", id);
        return agregadoRepository.findById(id)
            .map(agregadoMapper::toDto);
    }

    /**
     * Delete the agregado by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agregado : {}", id);

        agregadoRepository.deleteById(id);
    }
}
