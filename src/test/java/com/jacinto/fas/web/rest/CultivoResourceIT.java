package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.Cultivo;
import com.jacinto.fas.repository.CultivoRepository;
import com.jacinto.fas.service.CultivoService;
import com.jacinto.fas.service.dto.CultivoDTO;
import com.jacinto.fas.service.mapper.CultivoMapper;

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

import com.jacinto.fas.domain.enumeration.TipoCultivo;
/**
 * Integration tests for the {@link CultivoResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class CultivoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final TipoCultivo DEFAULT_TIPO = TipoCultivo.HASS;
    private static final TipoCultivo UPDATED_TIPO = TipoCultivo.REED;

    @Autowired
    private CultivoRepository cultivoRepository;

    @Autowired
    private CultivoMapper cultivoMapper;

    @Autowired
    private CultivoService cultivoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCultivoMockMvc;

    private Cultivo cultivo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cultivo createEntity(EntityManager em) {
        Cultivo cultivo = new Cultivo()
            .name(DEFAULT_NAME)
            .tipo(DEFAULT_TIPO);
        return cultivo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cultivo createUpdatedEntity(EntityManager em) {
        Cultivo cultivo = new Cultivo()
            .name(UPDATED_NAME)
            .tipo(UPDATED_TIPO);
        return cultivo;
    }

    @BeforeEach
    public void initTest() {
        cultivo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCultivo() throws Exception {
        int databaseSizeBeforeCreate = cultivoRepository.findAll().size();
        // Create the Cultivo
        CultivoDTO cultivoDTO = cultivoMapper.toDto(cultivo);
        restCultivoMockMvc.perform(post("/api/cultivos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cultivoDTO)))
            .andExpect(status().isCreated());

        // Validate the Cultivo in the database
        List<Cultivo> cultivoList = cultivoRepository.findAll();
        assertThat(cultivoList).hasSize(databaseSizeBeforeCreate + 1);
        Cultivo testCultivo = cultivoList.get(cultivoList.size() - 1);
        assertThat(testCultivo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCultivo.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createCultivoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cultivoRepository.findAll().size();

        // Create the Cultivo with an existing ID
        cultivo.setId(1L);
        CultivoDTO cultivoDTO = cultivoMapper.toDto(cultivo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCultivoMockMvc.perform(post("/api/cultivos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cultivoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cultivo in the database
        List<Cultivo> cultivoList = cultivoRepository.findAll();
        assertThat(cultivoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cultivoRepository.findAll().size();
        // set the field null
        cultivo.setName(null);

        // Create the Cultivo, which fails.
        CultivoDTO cultivoDTO = cultivoMapper.toDto(cultivo);


        restCultivoMockMvc.perform(post("/api/cultivos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cultivoDTO)))
            .andExpect(status().isBadRequest());

        List<Cultivo> cultivoList = cultivoRepository.findAll();
        assertThat(cultivoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cultivoRepository.findAll().size();
        // set the field null
        cultivo.setTipo(null);

        // Create the Cultivo, which fails.
        CultivoDTO cultivoDTO = cultivoMapper.toDto(cultivo);


        restCultivoMockMvc.perform(post("/api/cultivos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cultivoDTO)))
            .andExpect(status().isBadRequest());

        List<Cultivo> cultivoList = cultivoRepository.findAll();
        assertThat(cultivoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCultivos() throws Exception {
        // Initialize the database
        cultivoRepository.saveAndFlush(cultivo);

        // Get all the cultivoList
        restCultivoMockMvc.perform(get("/api/cultivos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cultivo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())));
    }
    
    @Test
    @Transactional
    public void getCultivo() throws Exception {
        // Initialize the database
        cultivoRepository.saveAndFlush(cultivo);

        // Get the cultivo
        restCultivoMockMvc.perform(get("/api/cultivos/{id}", cultivo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cultivo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCultivo() throws Exception {
        // Get the cultivo
        restCultivoMockMvc.perform(get("/api/cultivos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCultivo() throws Exception {
        // Initialize the database
        cultivoRepository.saveAndFlush(cultivo);

        int databaseSizeBeforeUpdate = cultivoRepository.findAll().size();

        // Update the cultivo
        Cultivo updatedCultivo = cultivoRepository.findById(cultivo.getId()).get();
        // Disconnect from session so that the updates on updatedCultivo are not directly saved in db
        em.detach(updatedCultivo);
        updatedCultivo
            .name(UPDATED_NAME)
            .tipo(UPDATED_TIPO);
        CultivoDTO cultivoDTO = cultivoMapper.toDto(updatedCultivo);

        restCultivoMockMvc.perform(put("/api/cultivos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cultivoDTO)))
            .andExpect(status().isOk());

        // Validate the Cultivo in the database
        List<Cultivo> cultivoList = cultivoRepository.findAll();
        assertThat(cultivoList).hasSize(databaseSizeBeforeUpdate);
        Cultivo testCultivo = cultivoList.get(cultivoList.size() - 1);
        assertThat(testCultivo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCultivo.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingCultivo() throws Exception {
        int databaseSizeBeforeUpdate = cultivoRepository.findAll().size();

        // Create the Cultivo
        CultivoDTO cultivoDTO = cultivoMapper.toDto(cultivo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCultivoMockMvc.perform(put("/api/cultivos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cultivoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cultivo in the database
        List<Cultivo> cultivoList = cultivoRepository.findAll();
        assertThat(cultivoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCultivo() throws Exception {
        // Initialize the database
        cultivoRepository.saveAndFlush(cultivo);

        int databaseSizeBeforeDelete = cultivoRepository.findAll().size();

        // Delete the cultivo
        restCultivoMockMvc.perform(delete("/api/cultivos/{id}", cultivo.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cultivo> cultivoList = cultivoRepository.findAll();
        assertThat(cultivoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
