package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.EntidadCertificadora;
import com.jacinto.fas.repository.EntidadCertificadoraRepository;
import com.jacinto.fas.service.EntidadCertificadoraService;
import com.jacinto.fas.service.dto.EntidadCertificadoraDTO;
import com.jacinto.fas.service.mapper.EntidadCertificadoraMapper;

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

import com.jacinto.fas.domain.enumeration.TipoCertificadora;
/**
 * Integration tests for the {@link EntidadCertificadoraResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class EntidadCertificadoraResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final TipoCertificadora DEFAULT_TIPO = TipoCertificadora.BPA;
    private static final TipoCertificadora UPDATED_TIPO = TipoCertificadora.PREDIO_EXPORTADOR;

    @Autowired
    private EntidadCertificadoraRepository entidadCertificadoraRepository;

    @Autowired
    private EntidadCertificadoraMapper entidadCertificadoraMapper;

    @Autowired
    private EntidadCertificadoraService entidadCertificadoraService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntidadCertificadoraMockMvc;

    private EntidadCertificadora entidadCertificadora;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntidadCertificadora createEntity(EntityManager em) {
        EntidadCertificadora entidadCertificadora = new EntidadCertificadora()
            .name(DEFAULT_NAME)
            .tipo(DEFAULT_TIPO);
        return entidadCertificadora;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntidadCertificadora createUpdatedEntity(EntityManager em) {
        EntidadCertificadora entidadCertificadora = new EntidadCertificadora()
            .name(UPDATED_NAME)
            .tipo(UPDATED_TIPO);
        return entidadCertificadora;
    }

    @BeforeEach
    public void initTest() {
        entidadCertificadora = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntidadCertificadora() throws Exception {
        int databaseSizeBeforeCreate = entidadCertificadoraRepository.findAll().size();
        // Create the EntidadCertificadora
        EntidadCertificadoraDTO entidadCertificadoraDTO = entidadCertificadoraMapper.toDto(entidadCertificadora);
        restEntidadCertificadoraMockMvc.perform(post("/api/entidad-certificadoras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadCertificadoraDTO)))
            .andExpect(status().isCreated());

        // Validate the EntidadCertificadora in the database
        List<EntidadCertificadora> entidadCertificadoraList = entidadCertificadoraRepository.findAll();
        assertThat(entidadCertificadoraList).hasSize(databaseSizeBeforeCreate + 1);
        EntidadCertificadora testEntidadCertificadora = entidadCertificadoraList.get(entidadCertificadoraList.size() - 1);
        assertThat(testEntidadCertificadora.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEntidadCertificadora.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createEntidadCertificadoraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entidadCertificadoraRepository.findAll().size();

        // Create the EntidadCertificadora with an existing ID
        entidadCertificadora.setId(1L);
        EntidadCertificadoraDTO entidadCertificadoraDTO = entidadCertificadoraMapper.toDto(entidadCertificadora);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntidadCertificadoraMockMvc.perform(post("/api/entidad-certificadoras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadCertificadoraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntidadCertificadora in the database
        List<EntidadCertificadora> entidadCertificadoraList = entidadCertificadoraRepository.findAll();
        assertThat(entidadCertificadoraList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadCertificadoraRepository.findAll().size();
        // set the field null
        entidadCertificadora.setName(null);

        // Create the EntidadCertificadora, which fails.
        EntidadCertificadoraDTO entidadCertificadoraDTO = entidadCertificadoraMapper.toDto(entidadCertificadora);


        restEntidadCertificadoraMockMvc.perform(post("/api/entidad-certificadoras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadCertificadoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadCertificadora> entidadCertificadoraList = entidadCertificadoraRepository.findAll();
        assertThat(entidadCertificadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadCertificadoraRepository.findAll().size();
        // set the field null
        entidadCertificadora.setTipo(null);

        // Create the EntidadCertificadora, which fails.
        EntidadCertificadoraDTO entidadCertificadoraDTO = entidadCertificadoraMapper.toDto(entidadCertificadora);


        restEntidadCertificadoraMockMvc.perform(post("/api/entidad-certificadoras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadCertificadoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadCertificadora> entidadCertificadoraList = entidadCertificadoraRepository.findAll();
        assertThat(entidadCertificadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntidadCertificadoras() throws Exception {
        // Initialize the database
        entidadCertificadoraRepository.saveAndFlush(entidadCertificadora);

        // Get all the entidadCertificadoraList
        restEntidadCertificadoraMockMvc.perform(get("/api/entidad-certificadoras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entidadCertificadora.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())));
    }
    
    @Test
    @Transactional
    public void getEntidadCertificadora() throws Exception {
        // Initialize the database
        entidadCertificadoraRepository.saveAndFlush(entidadCertificadora);

        // Get the entidadCertificadora
        restEntidadCertificadoraMockMvc.perform(get("/api/entidad-certificadoras/{id}", entidadCertificadora.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entidadCertificadora.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEntidadCertificadora() throws Exception {
        // Get the entidadCertificadora
        restEntidadCertificadoraMockMvc.perform(get("/api/entidad-certificadoras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntidadCertificadora() throws Exception {
        // Initialize the database
        entidadCertificadoraRepository.saveAndFlush(entidadCertificadora);

        int databaseSizeBeforeUpdate = entidadCertificadoraRepository.findAll().size();

        // Update the entidadCertificadora
        EntidadCertificadora updatedEntidadCertificadora = entidadCertificadoraRepository.findById(entidadCertificadora.getId()).get();
        // Disconnect from session so that the updates on updatedEntidadCertificadora are not directly saved in db
        em.detach(updatedEntidadCertificadora);
        updatedEntidadCertificadora
            .name(UPDATED_NAME)
            .tipo(UPDATED_TIPO);
        EntidadCertificadoraDTO entidadCertificadoraDTO = entidadCertificadoraMapper.toDto(updatedEntidadCertificadora);

        restEntidadCertificadoraMockMvc.perform(put("/api/entidad-certificadoras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadCertificadoraDTO)))
            .andExpect(status().isOk());

        // Validate the EntidadCertificadora in the database
        List<EntidadCertificadora> entidadCertificadoraList = entidadCertificadoraRepository.findAll();
        assertThat(entidadCertificadoraList).hasSize(databaseSizeBeforeUpdate);
        EntidadCertificadora testEntidadCertificadora = entidadCertificadoraList.get(entidadCertificadoraList.size() - 1);
        assertThat(testEntidadCertificadora.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEntidadCertificadora.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntidadCertificadora() throws Exception {
        int databaseSizeBeforeUpdate = entidadCertificadoraRepository.findAll().size();

        // Create the EntidadCertificadora
        EntidadCertificadoraDTO entidadCertificadoraDTO = entidadCertificadoraMapper.toDto(entidadCertificadora);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntidadCertificadoraMockMvc.perform(put("/api/entidad-certificadoras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadCertificadoraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntidadCertificadora in the database
        List<EntidadCertificadora> entidadCertificadoraList = entidadCertificadoraRepository.findAll();
        assertThat(entidadCertificadoraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntidadCertificadora() throws Exception {
        // Initialize the database
        entidadCertificadoraRepository.saveAndFlush(entidadCertificadora);

        int databaseSizeBeforeDelete = entidadCertificadoraRepository.findAll().size();

        // Delete the entidadCertificadora
        restEntidadCertificadoraMockMvc.perform(delete("/api/entidad-certificadoras/{id}", entidadCertificadora.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntidadCertificadora> entidadCertificadoraList = entidadCertificadoraRepository.findAll();
        assertThat(entidadCertificadoraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
