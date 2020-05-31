package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.Finca;
import com.jacinto.fas.repository.FincaRepository;
import com.jacinto.fas.service.FincaService;
import com.jacinto.fas.service.dto.FincaDTO;
import com.jacinto.fas.service.mapper.FincaMapper;

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
 * Integration tests for the {@link FincaResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class FincaResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ELEVATION = 1;
    private static final Integer UPDATED_ELEVATION = 2;

    private static final String DEFAULT_GPS = "AAAAAAAAAA";
    private static final String UPDATED_GPS = "BBBBBBBBBB";

    @Autowired
    private FincaRepository fincaRepository;

    @Autowired
    private FincaMapper fincaMapper;

    @Autowired
    private FincaService fincaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFincaMockMvc;

    private Finca finca;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Finca createEntity(EntityManager em) {
        Finca finca = new Finca()
            .name(DEFAULT_NAME)
            .elevation(DEFAULT_ELEVATION)
            .gps(DEFAULT_GPS);
        return finca;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Finca createUpdatedEntity(EntityManager em) {
        Finca finca = new Finca()
            .name(UPDATED_NAME)
            .elevation(UPDATED_ELEVATION)
            .gps(UPDATED_GPS);
        return finca;
    }

    @BeforeEach
    public void initTest() {
        finca = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinca() throws Exception {
        int databaseSizeBeforeCreate = fincaRepository.findAll().size();
        // Create the Finca
        FincaDTO fincaDTO = fincaMapper.toDto(finca);
        restFincaMockMvc.perform(post("/api/fincas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fincaDTO)))
            .andExpect(status().isCreated());

        // Validate the Finca in the database
        List<Finca> fincaList = fincaRepository.findAll();
        assertThat(fincaList).hasSize(databaseSizeBeforeCreate + 1);
        Finca testFinca = fincaList.get(fincaList.size() - 1);
        assertThat(testFinca.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFinca.getElevation()).isEqualTo(DEFAULT_ELEVATION);
        assertThat(testFinca.getGps()).isEqualTo(DEFAULT_GPS);
    }

    @Test
    @Transactional
    public void createFincaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fincaRepository.findAll().size();

        // Create the Finca with an existing ID
        finca.setId(1L);
        FincaDTO fincaDTO = fincaMapper.toDto(finca);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFincaMockMvc.perform(post("/api/fincas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fincaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Finca in the database
        List<Finca> fincaList = fincaRepository.findAll();
        assertThat(fincaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fincaRepository.findAll().size();
        // set the field null
        finca.setName(null);

        // Create the Finca, which fails.
        FincaDTO fincaDTO = fincaMapper.toDto(finca);


        restFincaMockMvc.perform(post("/api/fincas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fincaDTO)))
            .andExpect(status().isBadRequest());

        List<Finca> fincaList = fincaRepository.findAll();
        assertThat(fincaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkElevationIsRequired() throws Exception {
        int databaseSizeBeforeTest = fincaRepository.findAll().size();
        // set the field null
        finca.setElevation(null);

        // Create the Finca, which fails.
        FincaDTO fincaDTO = fincaMapper.toDto(finca);


        restFincaMockMvc.perform(post("/api/fincas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fincaDTO)))
            .andExpect(status().isBadRequest());

        List<Finca> fincaList = fincaRepository.findAll();
        assertThat(fincaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFincas() throws Exception {
        // Initialize the database
        fincaRepository.saveAndFlush(finca);

        // Get all the fincaList
        restFincaMockMvc.perform(get("/api/fincas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(finca.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].elevation").value(hasItem(DEFAULT_ELEVATION)))
            .andExpect(jsonPath("$.[*].gps").value(hasItem(DEFAULT_GPS)));
    }
    
    @Test
    @Transactional
    public void getFinca() throws Exception {
        // Initialize the database
        fincaRepository.saveAndFlush(finca);

        // Get the finca
        restFincaMockMvc.perform(get("/api/fincas/{id}", finca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(finca.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.elevation").value(DEFAULT_ELEVATION))
            .andExpect(jsonPath("$.gps").value(DEFAULT_GPS));
    }
    @Test
    @Transactional
    public void getNonExistingFinca() throws Exception {
        // Get the finca
        restFincaMockMvc.perform(get("/api/fincas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinca() throws Exception {
        // Initialize the database
        fincaRepository.saveAndFlush(finca);

        int databaseSizeBeforeUpdate = fincaRepository.findAll().size();

        // Update the finca
        Finca updatedFinca = fincaRepository.findById(finca.getId()).get();
        // Disconnect from session so that the updates on updatedFinca are not directly saved in db
        em.detach(updatedFinca);
        updatedFinca
            .name(UPDATED_NAME)
            .elevation(UPDATED_ELEVATION)
            .gps(UPDATED_GPS);
        FincaDTO fincaDTO = fincaMapper.toDto(updatedFinca);

        restFincaMockMvc.perform(put("/api/fincas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fincaDTO)))
            .andExpect(status().isOk());

        // Validate the Finca in the database
        List<Finca> fincaList = fincaRepository.findAll();
        assertThat(fincaList).hasSize(databaseSizeBeforeUpdate);
        Finca testFinca = fincaList.get(fincaList.size() - 1);
        assertThat(testFinca.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFinca.getElevation()).isEqualTo(UPDATED_ELEVATION);
        assertThat(testFinca.getGps()).isEqualTo(UPDATED_GPS);
    }

    @Test
    @Transactional
    public void updateNonExistingFinca() throws Exception {
        int databaseSizeBeforeUpdate = fincaRepository.findAll().size();

        // Create the Finca
        FincaDTO fincaDTO = fincaMapper.toDto(finca);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFincaMockMvc.perform(put("/api/fincas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fincaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Finca in the database
        List<Finca> fincaList = fincaRepository.findAll();
        assertThat(fincaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinca() throws Exception {
        // Initialize the database
        fincaRepository.saveAndFlush(finca);

        int databaseSizeBeforeDelete = fincaRepository.findAll().size();

        // Delete the finca
        restFincaMockMvc.perform(delete("/api/fincas/{id}", finca.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Finca> fincaList = fincaRepository.findAll();
        assertThat(fincaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
