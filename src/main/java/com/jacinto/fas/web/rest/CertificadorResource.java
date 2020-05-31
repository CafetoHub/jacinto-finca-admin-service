package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.CertificadorService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.CertificadorDTO;

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
 * REST controller for managing {@link com.jacinto.fas.domain.Certificador}.
 */
@RestController
@RequestMapping("/api")
public class CertificadorResource {

    private final Logger log = LoggerFactory.getLogger(CertificadorResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceCertificador";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CertificadorService certificadorService;

    public CertificadorResource(CertificadorService certificadorService) {
        this.certificadorService = certificadorService;
    }

    /**
     * {@code POST  /certificadors} : Create a new certificador.
     *
     * @param certificadorDTO the certificadorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new certificadorDTO, or with status {@code 400 (Bad Request)} if the certificador has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/certificadors")
    public ResponseEntity<CertificadorDTO> createCertificador(@RequestBody CertificadorDTO certificadorDTO) throws URISyntaxException {
        log.debug("REST request to save Certificador : {}", certificadorDTO);
        if (certificadorDTO.getId() != null) {
            throw new BadRequestAlertException("A new certificador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificadorDTO result = certificadorService.save(certificadorDTO);
        return ResponseEntity.created(new URI("/api/certificadors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /certificadors} : Updates an existing certificador.
     *
     * @param certificadorDTO the certificadorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificadorDTO,
     * or with status {@code 400 (Bad Request)} if the certificadorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the certificadorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/certificadors")
    public ResponseEntity<CertificadorDTO> updateCertificador(@RequestBody CertificadorDTO certificadorDTO) throws URISyntaxException {
        log.debug("REST request to update Certificador : {}", certificadorDTO);
        if (certificadorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificadorDTO result = certificadorService.save(certificadorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, certificadorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /certificadors} : get all the certificadors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of certificadors in body.
     */
    @GetMapping("/certificadors")
    public List<CertificadorDTO> getAllCertificadors() {
        log.debug("REST request to get all Certificadors");
        return certificadorService.findAll();
    }

    /**
     * {@code GET  /certificadors/:id} : get the "id" certificador.
     *
     * @param id the id of the certificadorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the certificadorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/certificadors/{id}")
    public ResponseEntity<CertificadorDTO> getCertificador(@PathVariable Long id) {
        log.debug("REST request to get Certificador : {}", id);
        Optional<CertificadorDTO> certificadorDTO = certificadorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificadorDTO);
    }

    /**
     * {@code DELETE  /certificadors/:id} : delete the "id" certificador.
     *
     * @param id the id of the certificadorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/certificadors/{id}")
    public ResponseEntity<Void> deleteCertificador(@PathVariable Long id) {
        log.debug("REST request to delete Certificador : {}", id);

        certificadorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
