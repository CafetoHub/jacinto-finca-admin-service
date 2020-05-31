package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.CultivoService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.CultivoDTO;

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
 * REST controller for managing {@link com.jacinto.fas.domain.Cultivo}.
 */
@RestController
@RequestMapping("/api")
public class CultivoResource {

    private final Logger log = LoggerFactory.getLogger(CultivoResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceCultivo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CultivoService cultivoService;

    public CultivoResource(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    /**
     * {@code POST  /cultivos} : Create a new cultivo.
     *
     * @param cultivoDTO the cultivoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cultivoDTO, or with status {@code 400 (Bad Request)} if the cultivo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cultivos")
    public ResponseEntity<CultivoDTO> createCultivo(@Valid @RequestBody CultivoDTO cultivoDTO) throws URISyntaxException {
        log.debug("REST request to save Cultivo : {}", cultivoDTO);
        if (cultivoDTO.getId() != null) {
            throw new BadRequestAlertException("A new cultivo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CultivoDTO result = cultivoService.save(cultivoDTO);
        return ResponseEntity.created(new URI("/api/cultivos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cultivos} : Updates an existing cultivo.
     *
     * @param cultivoDTO the cultivoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cultivoDTO,
     * or with status {@code 400 (Bad Request)} if the cultivoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cultivoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cultivos")
    public ResponseEntity<CultivoDTO> updateCultivo(@Valid @RequestBody CultivoDTO cultivoDTO) throws URISyntaxException {
        log.debug("REST request to update Cultivo : {}", cultivoDTO);
        if (cultivoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CultivoDTO result = cultivoService.save(cultivoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cultivoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cultivos} : get all the cultivos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cultivos in body.
     */
    @GetMapping("/cultivos")
    public List<CultivoDTO> getAllCultivos() {
        log.debug("REST request to get all Cultivos");
        return cultivoService.findAll();
    }

    /**
     * {@code GET  /cultivos/:id} : get the "id" cultivo.
     *
     * @param id the id of the cultivoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cultivoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cultivos/{id}")
    public ResponseEntity<CultivoDTO> getCultivo(@PathVariable Long id) {
        log.debug("REST request to get Cultivo : {}", id);
        Optional<CultivoDTO> cultivoDTO = cultivoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cultivoDTO);
    }

    /**
     * {@code DELETE  /cultivos/:id} : delete the "id" cultivo.
     *
     * @param id the id of the cultivoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cultivos/{id}")
    public ResponseEntity<Void> deleteCultivo(@PathVariable Long id) {
        log.debug("REST request to delete Cultivo : {}", id);

        cultivoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
