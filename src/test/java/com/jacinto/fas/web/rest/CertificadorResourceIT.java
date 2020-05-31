package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.Certificador;
import com.jacinto.fas.repository.CertificadorRepository;
import com.jacinto.fas.service.CertificadorService;
import com.jacinto.fas.service.dto.CertificadorDTO;
import com.jacinto.fas.service.mapper.CertificadorMapper;

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
 * Integration tests for the {@link CertificadorResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class CertificadorResourceIT {

    @Autowired
    private CertificadorRepository certificadorRepository;

    @Autowired
    private CertificadorMapper certificadorMapper;

    @Autowired
    private CertificadorService certificadorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCertificadorMockMvc;

    private Certificador certificador;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificador createEntity(EntityManager em) {
        Certificador certificador = new Certificador();
        return certificador;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificador createUpdatedEntity(EntityManager em) {
        Certificador certificador = new Certificador();
        return certificador;
    }

    @BeforeEach
    public void initTest() {
        certificador = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificador() throws Exception {
        int databaseSizeBeforeCreate = certificadorRepository.findAll().size();
        // Create the Certificador
        CertificadorDTO certificadorDTO = certificadorMapper.toDto(certificador);
        restCertificadorMockMvc.perform(post("/api/certificadors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadorDTO)))
            .andExpect(status().isCreated());

        // Validate the Certificador in the database
        List<Certificador> certificadorList = certificadorRepository.findAll();
        assertThat(certificadorList).hasSize(databaseSizeBeforeCreate + 1);
        Certificador testCertificador = certificadorList.get(certificadorList.size() - 1);
    }

    @Test
    @Transactional
    public void createCertificadorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificadorRepository.findAll().size();

        // Create the Certificador with an existing ID
        certificador.setId(1L);
        CertificadorDTO certificadorDTO = certificadorMapper.toDto(certificador);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificadorMockMvc.perform(post("/api/certificadors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Certificador in the database
        List<Certificador> certificadorList = certificadorRepository.findAll();
        assertThat(certificadorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCertificadors() throws Exception {
        // Initialize the database
        certificadorRepository.saveAndFlush(certificador);

        // Get all the certificadorList
        restCertificadorMockMvc.perform(get("/api/certificadors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificador.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCertificador() throws Exception {
        // Initialize the database
        certificadorRepository.saveAndFlush(certificador);

        // Get the certificador
        restCertificadorMockMvc.perform(get("/api/certificadors/{id}", certificador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(certificador.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCertificador() throws Exception {
        // Get the certificador
        restCertificadorMockMvc.perform(get("/api/certificadors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificador() throws Exception {
        // Initialize the database
        certificadorRepository.saveAndFlush(certificador);

        int databaseSizeBeforeUpdate = certificadorRepository.findAll().size();

        // Update the certificador
        Certificador updatedCertificador = certificadorRepository.findById(certificador.getId()).get();
        // Disconnect from session so that the updates on updatedCertificador are not directly saved in db
        em.detach(updatedCertificador);
        CertificadorDTO certificadorDTO = certificadorMapper.toDto(updatedCertificador);

        restCertificadorMockMvc.perform(put("/api/certificadors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadorDTO)))
            .andExpect(status().isOk());

        // Validate the Certificador in the database
        List<Certificador> certificadorList = certificadorRepository.findAll();
        assertThat(certificadorList).hasSize(databaseSizeBeforeUpdate);
        Certificador testCertificador = certificadorList.get(certificadorList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificador() throws Exception {
        int databaseSizeBeforeUpdate = certificadorRepository.findAll().size();

        // Create the Certificador
        CertificadorDTO certificadorDTO = certificadorMapper.toDto(certificador);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificadorMockMvc.perform(put("/api/certificadors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificadorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Certificador in the database
        List<Certificador> certificadorList = certificadorRepository.findAll();
        assertThat(certificadorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificador() throws Exception {
        // Initialize the database
        certificadorRepository.saveAndFlush(certificador);

        int databaseSizeBeforeDelete = certificadorRepository.findAll().size();

        // Delete the certificador
        restCertificadorMockMvc.perform(delete("/api/certificadors/{id}", certificador.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Certificador> certificadorList = certificadorRepository.findAll();
        assertThat(certificadorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
