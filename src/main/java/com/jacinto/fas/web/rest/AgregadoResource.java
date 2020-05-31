package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.AgregadoService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.AgregadoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jacinto.fas.domain.Agregado}.
 */
@RestController
@RequestMapping("/api")
public class AgregadoResource {

    private final Logger log = LoggerFactory.getLogger(AgregadoResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceAgregado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgregadoService agregadoService;

    public AgregadoResource(AgregadoService agregadoService) {
        this.agregadoService = agregadoService;
    }

    /**
     * {@code POST  /agregados} : Create a new agregado.
     *
     * @param agregadoDTO the agregadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agregadoDTO, or with status {@code 400 (Bad Request)} if the agregado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agregados")
    public ResponseEntity<AgregadoDTO> createAgregado(@RequestBody AgregadoDTO agregadoDTO) throws URISyntaxException {
        log.debug("REST request to save Agregado : {}", agregadoDTO);
        if (agregadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new agregado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgregadoDTO result = agregadoService.save(agregadoDTO);
        return ResponseEntity.created(new URI("/api/agregados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /agregados} : Updates an existing agregado.
     *
     * @param agregadoDTO the agregadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agregadoDTO,
     * or with status {@code 400 (Bad Request)} if the agregadoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agregadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/agregados")
    public ResponseEntity<AgregadoDTO> updateAgregado(@RequestBody AgregadoDTO agregadoDTO) throws URISyntaxException {
        log.debug("REST request to update Agregado : {}", agregadoDTO);
        if (agregadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgregadoDTO result = agregadoService.save(agregadoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agregadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agregados} : get all the agregados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agregados in body.
     */
    @GetMapping("/agregados")
    public List<AgregadoDTO> getAllAgregados() {
        log.debug("REST request to get all Agregados");
        return agregadoService.findAll();
    }

    /**
     * {@code GET  /agregados/:id} : get the "id" agregado.
     *
     * @param id the id of the agregadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agregadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agregados/{id}")
    public ResponseEntity<AgregadoDTO> getAgregado(@PathVariable Long id) {
        log.debug("REST request to get Agregado : {}", id);
        Optional<AgregadoDTO> agregadoDTO = agregadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agregadoDTO);
    }

    /**
     * {@code DELETE  /agregados/:id} : delete the "id" agregado.
     *
     * @param id the id of the agregadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agregados/{id}")
    public ResponseEntity<Void> deleteAgregado(@PathVariable Long id) {
        log.debug("REST request to delete Agregado : {}", id);

        agregadoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
