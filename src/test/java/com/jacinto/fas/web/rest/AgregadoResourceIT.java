package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.Agregado;
import com.jacinto.fas.repository.AgregadoRepository;
import com.jacinto.fas.service.AgregadoService;
import com.jacinto.fas.service.dto.AgregadoDTO;
import com.jacinto.fas.service.mapper.AgregadoMapper;

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
 * Integration tests for the {@link AgregadoResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class AgregadoResourceIT {

    private static final String DEFAULT_CEDULA = "AAAAAAAAAA";
    private static final String UPDATED_CEDULA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    @Autowired
    private AgregadoRepository agregadoRepository;

    @Autowired
    private AgregadoMapper agregadoMapper;

    @Autowired
    private AgregadoService agregadoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAgregadoMockMvc;

    private Agregado agregado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agregado createEntity(EntityManager em) {
        Agregado agregado = new Agregado()
            .cedula(DEFAULT_CEDULA)
            .active(DEFAULT_ACTIVE);
        return agregado;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agregado createUpdatedEntity(EntityManager em) {
        Agregado agregado = new Agregado()
            .cedula(UPDATED_CEDULA)
            .active(UPDATED_ACTIVE);
        return agregado;
    }

    @BeforeEach
    public void initTest() {
        agregado = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgregado() throws Exception {
        int databaseSizeBeforeCreate = agregadoRepository.findAll().size();
        // Create the Agregado
        AgregadoDTO agregadoDTO = agregadoMapper.toDto(agregado);
        restAgregadoMockMvc.perform(post("/api/agregados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agregadoDTO)))
            .andExpect(status().isCreated());

        // Validate the Agregado in the database
        List<Agregado> agregadoList = agregadoRepository.findAll();
        assertThat(agregadoList).hasSize(databaseSizeBeforeCreate + 1);
        Agregado testAgregado = agregadoList.get(agregadoList.size() - 1);
        assertThat(testAgregado.getCedula()).isEqualTo(DEFAULT_CEDULA);
        assertThat(testAgregado.isActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void createAgregadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agregadoRepository.findAll().size();

        // Create the Agregado with an existing ID
        agregado.setId(1L);
        AgregadoDTO agregadoDTO = agregadoMapper.toDto(agregado);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgregadoMockMvc.perform(post("/api/agregados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agregadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agregado in the database
        List<Agregado> agregadoList = agregadoRepository.findAll();
        assertThat(agregadoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAgregados() throws Exception {
        // Initialize the database
        agregadoRepository.saveAndFlush(agregado);

        // Get all the agregadoList
        restAgregadoMockMvc.perform(get("/api/agregados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agregado.getId().intValue())))
            .andExpect(jsonPath("$.[*].cedula").value(hasItem(DEFAULT_CEDULA)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAgregado() throws Exception {
        // Initialize the database
        agregadoRepository.saveAndFlush(agregado);

        // Get the agregado
        restAgregadoMockMvc.perform(get("/api/agregados/{id}", agregado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(agregado.getId().intValue()))
            .andExpect(jsonPath("$.cedula").value(DEFAULT_CEDULA))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAgregado() throws Exception {
        // Get the agregado
        restAgregadoMockMvc.perform(get("/api/agregados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgregado() throws Exception {
        // Initialize the database
        agregadoRepository.saveAndFlush(agregado);

        int databaseSizeBeforeUpdate = agregadoRepository.findAll().size();

        // Update the agregado
        Agregado updatedAgregado = agregadoRepository.findById(agregado.getId()).get();
        // Disconnect from session so that the updates on updatedAgregado are not directly saved in db
        em.detach(updatedAgregado);
        updatedAgregado
            .cedula(UPDATED_CEDULA)
            .active(UPDATED_ACTIVE);
        AgregadoDTO agregadoDTO = agregadoMapper.toDto(updatedAgregado);

        restAgregadoMockMvc.perform(put("/api/agregados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agregadoDTO)))
            .andExpect(status().isOk());

        // Validate the Agregado in the database
        List<Agregado> agregadoList = agregadoRepository.findAll();
        assertThat(agregadoList).hasSize(databaseSizeBeforeUpdate);
        Agregado testAgregado = agregadoList.get(agregadoList.size() - 1);
        assertThat(testAgregado.getCedula()).isEqualTo(UPDATED_CEDULA);
        assertThat(testAgregado.isActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingAgregado() throws Exception {
        int databaseSizeBeforeUpdate = agregadoRepository.findAll().size();

        // Create the Agregado
        AgregadoDTO agregadoDTO = agregadoMapper.toDto(agregado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgregadoMockMvc.perform(put("/api/agregados").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agregadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agregado in the database
        List<Agregado> agregadoList = agregadoRepository.findAll();
        assertThat(agregadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAgregado() throws Exception {
        // Initialize the database
        agregadoRepository.saveAndFlush(agregado);

        int databaseSizeBeforeDelete = agregadoRepository.findAll().size();

        // Delete the agregado
        restAgregadoMockMvc.perform(delete("/api/agregados/{id}", agregado.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Agregado> agregadoList = agregadoRepository.findAll();
        assertThat(agregadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
