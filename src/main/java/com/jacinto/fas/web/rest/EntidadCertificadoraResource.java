package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.EntidadCertificadoraService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.EntidadCertificadoraDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jacinto.fas.domain.EntidadCertificadora}.
 */
@RestController
@RequestMapping("/api")
public class EntidadCertificadoraResource {

    private final Logger log = LoggerFactory.getLogger(EntidadCertificadoraResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceEntidadCertificadora";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntidadCertificadoraService entidadCertificadoraService;

    public EntidadCertificadoraResource(EntidadCertificadoraService entidadCertificadoraService) {
        this.entidadCertificadoraService = entidadCertificadoraService;
    }

    /**
     * {@code POST  /entidad-certificadoras} : Create a new entidadCertificadora.
     *
     * @param entidadCertificadoraDTO the entidadCertificadoraDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entidadCertificadoraDTO, or with status {@code 400 (Bad Request)} if the entidadCertificadora has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entidad-certificadoras")
    public ResponseEntity<EntidadCertificadoraDTO> createEntidadCertificadora(@Valid @RequestBody EntidadCertificadoraDTO entidadCertificadoraDTO) throws URISyntaxException {
        log.debug("REST request to save EntidadCertificadora : {}", entidadCertificadoraDTO);
        if (entidadCertificadoraDTO.getId() != null) {
            throw new BadRequestAlertException("A new entidadCertificadora cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntidadCertificadoraDTO result = entidadCertificadoraService.save(entidadCertificadoraDTO);
        return ResponseEntity.created(new URI("/api/entidad-certificadoras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entidad-certificadoras} : Updates an existing entidadCertificadora.
     *
     * @param entidadCertificadoraDTO the entidadCertificadoraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entidadCertificadoraDTO,
     * or with status {@code 400 (Bad Request)} if the entidadCertificadoraDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entidadCertificadoraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entidad-certificadoras")
    public ResponseEntity<EntidadCertificadoraDTO> updateEntidadCertificadora(@Valid @RequestBody EntidadCertificadoraDTO entidadCertificadoraDTO) throws URISyntaxException {
        log.debug("REST request to update EntidadCertificadora : {}", entidadCertificadoraDTO);
        if (entidadCertificadoraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntidadCertificadoraDTO result = entidadCertificadoraService.save(entidadCertificadoraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entidadCertificadoraDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entidad-certificadoras} : get all the entidadCertificadoras.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entidadCertificadoras in body.
     */
    @GetMapping("/entidad-certificadoras")
    public List<EntidadCertificadoraDTO> getAllEntidadCertificadoras() {
        log.debug("REST request to get all EntidadCertificadoras");
        return entidadCertificadoraService.findAll();
    }

    /**
     * {@code GET  /entidad-certificadoras/:id} : get the "id" entidadCertificadora.
     *
     * @param id the id of the entidadCertificadoraDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entidadCertificadoraDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entidad-certificadoras/{id}")
    public ResponseEntity<EntidadCertificadoraDTO> getEntidadCertificadora(@PathVariable Long id) {
        log.debug("REST request to get EntidadCertificadora : {}", id);
        Optional<EntidadCertificadoraDTO> entidadCertificadoraDTO = entidadCertificadoraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entidadCertificadoraDTO);
    }

    /**
     * {@code DELETE  /entidad-certificadoras/:id} : delete the "id" entidadCertificadora.
     *
     * @param id the id of the entidadCertificadoraDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entidad-certificadoras/{id}")
    public ResponseEntity<Void> deleteEntidadCertificadora(@PathVariable Long id) {
        log.debug("REST request to delete EntidadCertificadora : {}", id);

        entidadCertificadoraService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
