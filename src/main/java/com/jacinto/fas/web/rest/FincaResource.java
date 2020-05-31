package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.FincaService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.FincaDTO;

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
 * REST controller for managing {@link com.jacinto.fas.domain.Finca}.
 */
@RestController
@RequestMapping("/api")
public class FincaResource {

    private final Logger log = LoggerFactory.getLogger(FincaResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceFinca";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FincaService fincaService;

    public FincaResource(FincaService fincaService) {
        this.fincaService = fincaService;
    }

    /**
     * {@code POST  /fincas} : Create a new finca.
     *
     * @param fincaDTO the fincaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fincaDTO, or with status {@code 400 (Bad Request)} if the finca has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fincas")
    public ResponseEntity<FincaDTO> createFinca(@Valid @RequestBody FincaDTO fincaDTO) throws URISyntaxException {
        log.debug("REST request to save Finca : {}", fincaDTO);
        if (fincaDTO.getId() != null) {
            throw new BadRequestAlertException("A new finca cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FincaDTO result = fincaService.save(fincaDTO);
        return ResponseEntity.created(new URI("/api/fincas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fincas} : Updates an existing finca.
     *
     * @param fincaDTO the fincaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fincaDTO,
     * or with status {@code 400 (Bad Request)} if the fincaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fincaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fincas")
    public ResponseEntity<FincaDTO> updateFinca(@Valid @RequestBody FincaDTO fincaDTO) throws URISyntaxException {
        log.debug("REST request to update Finca : {}", fincaDTO);
        if (fincaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FincaDTO result = fincaService.save(fincaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fincaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fincas} : get all the fincas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fincas in body.
     */
    @GetMapping("/fincas")
    public List<FincaDTO> getAllFincas() {
        log.debug("REST request to get all Fincas");
        return fincaService.findAll();
    }

    /**
     * {@code GET  /fincas/:id} : get the "id" finca.
     *
     * @param id the id of the fincaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fincaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fincas/{id}")
    public ResponseEntity<FincaDTO> getFinca(@PathVariable Long id) {
        log.debug("REST request to get Finca : {}", id);
        Optional<FincaDTO> fincaDTO = fincaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fincaDTO);
    }

    /**
     * {@code DELETE  /fincas/:id} : delete the "id" finca.
     *
     * @param id the id of the fincaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fincas/{id}")
    public ResponseEntity<Void> deleteFinca(@PathVariable Long id) {
        log.debug("REST request to delete Finca : {}", id);

        fincaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
