package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.Certificado;
import com.jacinto.fas.repository.CertificadoRepository;
import com.jacinto.fas.service.CertificadoService;
import com.jacinto.fas.service.dto.CertificadoDTO;
import com.jacinto.fas.service.mapper.CertificadoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CertificadoResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class CertificadoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_GIVEN = "AAAAAAAAAA";
    private static final String UPDATED_DATE_GIVEN = "BBBBBBBBBB";

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private CertificadoMapper certificadoMapper;

    @Autowired
    private CertificadoService certificadoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCertificadoMockMvc;

    private Certificado certificado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificado createEntity(EntityManager em) {
        Certificado certificado = new Certificado()
            .name(DEFAULT_NAME)
            .dateGiven(DEFAULT_DATE_GIVEN);
        return certificado;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificado createUpdatedEntity(EntityManager em) {
        Certificado certificado = new Certificado()
            .name(UPDATED_NAME)
            .dateGiven(UPDATED_DATE_GIVEN);
        return certificado;
    }

    @BeforeEach
    public void initTest() {
        certificado = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificado() throws Exception {
        int databaseSizeBeforeCreate = certificadoRepository.findAll().size();
        // Create the Certificado
        CertificadoDTO certificadoDTO = certificadoMapper.toDto(certificado);
        restCertificadoMockMvc.perform(post("/api/certificados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadoDTO)))
            .andExpect(status().isCreated());

        // Validate the Certificado in the database
        List<Certificado> certificadoList = certificadoRepository.findAll();
        assertThat(certificadoList).hasSize(databaseSizeBeforeCreate + 1);
        Certificado testCertificado = certificadoList.get(certificadoList.size() - 1);
        assertThat(testCertificado.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCertificado.getDateGiven()).isEqualTo(DEFAULT_DATE_GIVEN);
    }

    @Test
    @Transactional
    public void createCertificadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificadoRepository.findAll().size();

        // Create the Certificado with an existing ID
        certificado.setId(1L);
        CertificadoDTO certificadoDTO = certificadoMapper.toDto(certificado);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificadoMockMvc.perform(post("/api/certificados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Certificado in the database
        List<Certificado> certificadoList = certificadoRepository.findAll();
        assertThat(certificadoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoRepository.findAll().size();
        // set the field null
        certificado.setName(null);

        // Create the Certificado, which fails.
        CertificadoDTO certificadoDTO = certificadoMapper.toDto(certificado);


        restCertificadoMockMvc.perform(post("/api/certificados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadoDTO)))
            .andExpect(status().isBadRequest());

        List<Certificado> certificadoList = certificadoRepository.findAll();
        assertThat(certificadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateGivenIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoRepository.findAll().size();
        // set the field null
        certificado.setDateGiven(null);

        // Create the Certificado, which fails.
        CertificadoDTO certificadoDTO = certificadoMapper.toDto(certificado);


        restCertificadoMockMvc.perform(post("/api/certificados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadoDTO)))
            .andExpect(status().isBadRequest());

        List<Certificado> certificadoList = certificadoRepository.findAll();
        assertThat(certificadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCertificados() throws Exception {
        // Initialize the database
        certificadoRepository.saveAndFlush(certificado);

        // Get all the certificadoList
        restCertificadoMockMvc.perform(get("/api/certificados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificado.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].dateGiven").value(hasItem(DEFAULT_DATE_GIVEN)));
    }
    
    @Test
    @Transactional
    public void getCertificado() throws Exception {
        // Initialize the database
        certificadoRepository.saveAndFlush(certificado);

        // Get the certificado
        restCertificadoMockMvc.perform(get("/api/certificados/{id}", certificado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(certificado.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.dateGiven").value(DEFAULT_DATE_GIVEN));
    }
    @Test
    @Transactional
    public void getNonExistingCertificado() throws Exception {
        // Get the certificado
        restCertificadoMockMvc.perform(get("/api/certificados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificado() throws Exception {
        // Initialize the database
        certificadoRepository.saveAndFlush(certificado);

        int databaseSizeBeforeUpdate = certificadoRepository.findAll().size();

        // Update the certificado
        Certificado updatedCertificado = certificadoRepository.findById(certificado.getId()).get();
        // Disconnect from session so that the updates on updatedCertificado are not directly saved in db
        em.detach(updatedCertificado);
        updatedCertificado
            .name(UPDATED_NAME)
            .dateGiven(UPDATED_DATE_GIVEN);
        CertificadoDTO certificadoDTO = certificadoMapper.toDto(updatedCertificado);

        restCertificadoMockMvc.perform(put("/api/certificados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadoDTO)))
            .andExpect(status().isOk());

        // Validate the Certificado in the database
        List<Certificado> certificadoList = certificadoRepository.findAll();
        assertThat(certificadoList).hasSize(databaseSizeBeforeUpdate);
        Certificado testCertificado = certificadoList.get(certificadoList.size() - 1);
        assertThat(testCertificado.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCertificado.getDateGiven()).isEqualTo(UPDATED_DATE_GIVEN);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificado() throws Exception {
        int databaseSizeBeforeUpdate = certificadoRepository.findAll().size();

        // Create the Certificado
        CertificadoDTO certificadoDTO = certificadoMapper.toDto(certificado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificadoMockMvc.perform(put("/api/certificados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Certificado in the database
        List<Certificado> certificadoList = certificadoRepository.findAll();
        assertThat(certificadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificado() throws Exception {
        // Initialize the database
        certificadoRepository.saveAndFlush(certificado);

        int databaseSizeBeforeDelete = certificadoRepository.findAll().size();

        // Delete the certificado
        restCertificadoMockMvc.perform(delete("/api/certificados/{id}", certificado.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Certificado> certificadoList = certificadoRepository.findAll();
        assertThat(certificadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
