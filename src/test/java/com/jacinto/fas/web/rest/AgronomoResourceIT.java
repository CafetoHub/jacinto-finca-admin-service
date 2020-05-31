package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.Agronomo;
import com.jacinto.fas.repository.AgronomoRepository;
import com.jacinto.fas.service.AgronomoService;
import com.jacinto.fas.service.dto.AgronomoDTO;
import com.jacinto.fas.service.mapper.AgronomoMapper;

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
 * Integration tests for the {@link AgronomoResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class AgronomoResourceIT {

    private static final String DEFAULT_CEDULA = "AAAAAAAAAA";
    private static final String UPDATED_CEDULA = "BBBBBBBBBB";

    private static final String DEFAULT_CEDULA_PROFESIONAL = "AAAAAAAAAA";
    private static final String UPDATED_CEDULA_PROFESIONAL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    @Autowired
    private AgronomoRepository agronomoRepository;

    @Autowired
    private AgronomoMapper agronomoMapper;

    @Autowired
    private AgronomoService agronomoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAgronomoMockMvc;

    private Agronomo agronomo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agronomo createEntity(EntityManager em) {
        Agronomo agronomo = new Agronomo()
            .cedula(DEFAULT_CEDULA)
            .cedulaProfesional(DEFAULT_CEDULA_PROFESIONAL)
            .active(DEFAULT_ACTIVE);
        return agronomo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agronomo createUpdatedEntity(EntityManager em) {
        Agronomo agronomo = new Agronomo()
            .cedula(UPDATED_CEDULA)
            .cedulaProfesional(UPDATED_CEDULA_PROFESIONAL)
            .active(UPDATED_ACTIVE);
        return agronomo;
    }

    @BeforeEach
    public void initTest() {
        agronomo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgronomo() throws Exception {
        int databaseSizeBeforeCreate = agronomoRepository.findAll().size();
        // Create the Agronomo
        AgronomoDTO agronomoDTO = agronomoMapper.toDto(agronomo);
        restAgronomoMockMvc.perform(post("/api/agronomos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agronomoDTO)))
            .andExpect(status().isCreated());

        // Validate the Agronomo in the database
        List<Agronomo> agronomoList = agronomoRepository.findAll();
        assertThat(agronomoList).hasSize(databaseSizeBeforeCreate + 1);
        Agronomo testAgronomo = agronomoList.get(agronomoList.size() - 1);
        assertThat(testAgronomo.getCedula()).isEqualTo(DEFAULT_CEDULA);
        assertThat(testAgronomo.getCedulaProfesional()).isEqualTo(DEFAULT_CEDULA_PROFESIONAL);
        assertThat(testAgronomo.isActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void createAgronomoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agronomoRepository.findAll().size();

        // Create the Agronomo with an existing ID
        agronomo.setId(1L);
        AgronomoDTO agronomoDTO = agronomoMapper.toDto(agronomo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgronomoMockMvc.perform(post("/api/agronomos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agronomoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agronomo in the database
        List<Agronomo> agronomoList = agronomoRepository.findAll();
        assertThat(agronomoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAgronomos() throws Exception {
        // Initialize the database
        agronomoRepository.saveAndFlush(agronomo);

        // Get all the agronomoList
        restAgronomoMockMvc.perform(get("/api/agronomos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agronomo.getId().intValue())))
            .andExpect(jsonPath("$.[*].cedula").value(hasItem(DEFAULT_CEDULA)))
            .andExpect(jsonPath("$.[*].cedulaProfesional").value(hasItem(DEFAULT_CEDULA_PROFESIONAL)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAgronomo() throws Exception {
        // Initialize the database
        agronomoRepository.saveAndFlush(agronomo);

        // Get the agronomo
        restAgronomoMockMvc.perform(get("/api/agronomos/{id}", agronomo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(agronomo.getId().intValue()))
            .andExpect(jsonPath("$.cedula").value(DEFAULT_CEDULA))
            .andExpect(jsonPath("$.cedulaProfesional").value(DEFAULT_CEDULA_PROFESIONAL))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAgronomo() throws Exception {
        // Get the agronomo
        restAgronomoMockMvc.perform(get("/api/agronomos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgronomo() throws Exception {
        // Initialize the database
        agronomoRepository.saveAndFlush(agronomo);

        int databaseSizeBeforeUpdate = agronomoRepository.findAll().size();

        // Update the agronomo
        Agronomo updatedAgronomo = agronomoRepository.findById(agronomo.getId()).get();
        // Disconnect from session so that the updates on updatedAgronomo are not directly saved in db
        em.detach(updatedAgronomo);
        updatedAgronomo
            .cedula(UPDATED_CEDULA)
            .cedulaProfesional(UPDATED_CEDULA_PROFESIONAL)
            .active(UPDATED_ACTIVE);
        AgronomoDTO agronomoDTO = agronomoMapper.toDto(updatedAgronomo);

        restAgronomoMockMvc.perform(put("/api/agronomos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agronomoDTO)))
            .andExpect(status().isOk());

        // Validate the Agronomo in the database
        List<Agronomo> agronomoList = agronomoRepository.findAll();
        assertThat(agronomoList).hasSize(databaseSizeBeforeUpdate);
        Agronomo testAgronomo = agronomoList.get(agronomoList.size() - 1);
        assertThat(testAgronomo.getCedula()).isEqualTo(UPDATED_CEDULA);
        assertThat(testAgronomo.getCedulaProfesional()).isEqualTo(UPDATED_CEDULA_PROFESIONAL);
        assertThat(testAgronomo.isActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingAgronomo() throws Exception {
        int databaseSizeBeforeUpdate = agronomoRepository.findAll().size();

        // Create the Agronomo
        AgronomoDTO agronomoDTO = agronomoMapper.toDto(agronomo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgronomoMockMvc.perform(put("/api/agronomos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agronomoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agronomo in the database
        List<Agronomo> agronomoList = agronomoRepository.findAll();
        assertThat(agronomoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAgronomo() throws Exception {
        // Initialize the database
        agronomoRepository.saveAndFlush(agronomo);

        int databaseSizeBeforeDelete = agronomoRepository.findAll().size();

        // Delete the agronomo
        restAgronomoMockMvc.perform(delete("/api/agronomos/{id}", agronomo.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Agronomo> agronomoList = agronomoRepository.findAll();
        assertThat(agronomoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
