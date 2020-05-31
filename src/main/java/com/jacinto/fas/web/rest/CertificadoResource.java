package com.jacinto.fas.web.rest;

import com.jacinto.fas.service.CertificadoService;
import com.jacinto.fas.web.rest.errors.BadRequestAlertException;
import com.jacinto.fas.service.dto.CertificadoDTO;

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
 * REST controller for managing {@link com.jacinto.fas.domain.Certificado}.
 */
@RestController
@RequestMapping("/api")
public class CertificadoResource {

    private final Logger log = LoggerFactory.getLogger(CertificadoResource.class);

    private static final String ENTITY_NAME = "jacintoFincaAdminServiceCertificado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CertificadoService certificadoService;

    public CertificadoResource(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    /**
     * {@code POST  /certificados} : Create a new certificado.
     *
     * @param certificadoDTO the certificadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new certificadoDTO, or with status {@code 400 (Bad Request)} if the certificado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/certificados")
    public ResponseEntity<CertificadoDTO> createCertificado(@Valid @RequestBody CertificadoDTO certificadoDTO) throws URISyntaxException {
        log.debug("REST request to save Certificado : {}", certificadoDTO);
        if (certificadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new certificado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificadoDTO result = certificadoService.save(certificadoDTO);
        return ResponseEntity.created(new URI("/api/certificados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /certificados} : Updates an existing certificado.
     *
     * @param certificadoDTO the certificadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificadoDTO,
     * or with status {@code 400 (Bad Request)} if the certificadoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the certificadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/certificados")
    public ResponseEntity<CertificadoDTO> updateCertificado(@Valid @RequestBody CertificadoDTO certificadoDTO) throws URISyntaxException {
        log.debug("REST request to update Certificado : {}", certificadoDTO);
        if (certificadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificadoDTO result = certificadoService.save(certificadoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, certificadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /certificados} : get all the certificados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of certificados in body.
     */
    @GetMapping("/certificados")
    public List<CertificadoDTO> getAllCertificados() {
        log.debug("REST request to get all Certificados");
        return certificadoService.findAll();
    }

    /**
     * {@code GET  /certificados/:id} : get the "id" certificado.
     *
     * @param id the id of the certificadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the certificadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/certificados/{id}")
    public ResponseEntity<CertificadoDTO> getCertificado(@PathVariable Long id) {
        log.debug("REST request to get Certificado : {}", id);
        Optional<CertificadoDTO> certificadoDTO = certificadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificadoDTO);
    }

    /**
     * {@code DELETE  /certificados/:id} : delete the "id" certificado.
     *
     * @param id the id of the certificadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/certificados/{id}")
    public ResponseEntity<Void> deleteCertificado(@PathVariable Long id) {
        log.debug("REST request to delete Certificado : {}", id);

        certificadoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
