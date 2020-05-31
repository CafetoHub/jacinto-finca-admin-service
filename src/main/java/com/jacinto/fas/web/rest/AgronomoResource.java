package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.AgronomoService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.AgronomoDTO;

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
 * REST controller for managing {@link com.jacinto.fas.domain.Agronomo}.
 */
@RestController
@RequestMapping("/api")
public class AgronomoResource {

    private final Logger log = LoggerFactory.getLogger(AgronomoResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceAgronomo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgronomoService agronomoService;

    public AgronomoResource(AgronomoService agronomoService) {
        this.agronomoService = agronomoService;
    }

    /**
     * {@code POST  /agronomos} : Create a new agronomo.
     *
     * @param agronomoDTO the agronomoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agronomoDTO, or with status {@code 400 (Bad Request)} if the agronomo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agronomos")
    public ResponseEntity<AgronomoDTO> createAgronomo(@RequestBody AgronomoDTO agronomoDTO) throws URISyntaxException {
        log.debug("REST request to save Agronomo : {}", agronomoDTO);
        if (agronomoDTO.getId() != null) {
            throw new BadRequestAlertException("A new agronomo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgronomoDTO result = agronomoService.save(agronomoDTO);
        return ResponseEntity.created(new URI("/api/agronomos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /agronomos} : Updates an existing agronomo.
     *
     * @param agronomoDTO the agronomoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agronomoDTO,
     * or with status {@code 400 (Bad Request)} if the agronomoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agronomoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/agronomos")
    public ResponseEntity<AgronomoDTO> updateAgronomo(@RequestBody AgronomoDTO agronomoDTO) throws URISyntaxException {
        log.debug("REST request to update Agronomo : {}", agronomoDTO);
        if (agronomoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgronomoDTO result = agronomoService.save(agronomoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agronomoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agronomos} : get all the agronomos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agronomos in body.
     */
    @GetMapping("/agronomos")
    public List<AgronomoDTO> getAllAgronomos() {
        log.debug("REST request to get all Agronomos");
        return agronomoService.findAll();
    }

    /**
     * {@code GET  /agronomos/:id} : get the "id" agronomo.
     *
     * @param id the id of the agronomoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agronomoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agronomos/{id}")
    public ResponseEntity<AgronomoDTO> getAgronomo(@PathVariable Long id) {
        log.debug("REST request to get Agronomo : {}", id);
        Optional<AgronomoDTO> agronomoDTO = agronomoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agronomoDTO);
    }

    /**
     * {@code DELETE  /agronomos/:id} : delete the "id" agronomo.
     *
     * @param id the id of the agronomoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agronomos/{id}")
    public ResponseEntity<Void> deleteAgronomo(@PathVariable Long id) {
        log.debug("REST request to delete Agronomo : {}", id);

        agronomoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
