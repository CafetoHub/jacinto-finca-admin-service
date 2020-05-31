package com.jacinto.fas.web.rest;

import com.jacinto.fas.JacintoFincaAdminServiceApp;
import com.jacinto.fas.config.SecurityBeanOverrideConfiguration;
import com.jacinto.fas.domain.Lote;
import com.jacinto.fas.repository.LoteRepository;
import com.jacinto.fas.service.LoteService;
import com.jacinto.fas.service.dto.LoteDTO;
import com.jacinto.fas.service.mapper.LoteMapper;

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
 * Integration tests for the {@link LoteResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, JacintoFincaAdminServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class LoteResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_EXTENSION = 1L;
    private static final Long UPDATED_EXTENSION = 2L;

    private static final Long DEFAULT_ELEVACION = 1L;
    private static final Long UPDATED_ELEVACION = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private LoteMapper loteMapper;

    @Autowired
    private LoteService loteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoteMockMvc;

    private Lote lote;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lote createEntity(EntityManager em) {
        Lote lote = new Lote()
            .name(DEFAULT_NAME)
            .extension(DEFAULT_EXTENSION)
            .elevacion(DEFAULT_ELEVACION)
            .description(DEFAULT_DESCRIPTION);
        return lote;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lote createUpdatedEntity(EntityManager em) {
        Lote lote = new Lote()
            .name(UPDATED_NAME)
            .extension(UPDATED_EXTENSION)
            .elevacion(UPDATED_ELEVACION)
            .description(UPDATED_DESCRIPTION);
        return lote;
    }

    @BeforeEach
    public void initTest() {
        lote = createEntity(em);
    }

    @Test
    @Transactional
    public void createLote() throws Exception {
        int databaseSizeBeforeCreate = loteRepository.findAll().size();
        // Create the Lote
        LoteDTO loteDTO = loteMapper.toDto(lote);
        restLoteMockMvc.perform(post("/api/lotes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteDTO)))
            .andExpect(status().isCreated());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeCreate + 1);
        Lote testLote = loteList.get(loteList.size() - 1);
        assertThat(testLote.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLote.getExtension()).isEqualTo(DEFAULT_EXTENSION);
        assertThat(testLote.getElevacion()).isEqualTo(DEFAULT_ELEVACION);
        assertThat(testLote.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createLoteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loteRepository.findAll().size();

        // Create the Lote with an existing ID
        lote.setId(1L);
        LoteDTO loteDTO = loteMapper.toDto(lote);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoteMockMvc.perform(post("/api/lotes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteRepository.findAll().size();
        // set the field null
        lote.setName(null);

        // Create the Lote, which fails.
        LoteDTO loteDTO = loteMapper.toDto(lote);


        restLoteMockMvc.perform(post("/api/lotes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteDTO)))
            .andExpect(status().isBadRequest());

        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtensionIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteRepository.findAll().size();
        // set the field null
        lote.setExtension(null);

        // Create the Lote, which fails.
        LoteDTO loteDTO = loteMapper.toDto(lote);


        restLoteMockMvc.perform(post("/api/lotes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteDTO)))
            .andExpect(status().isBadRequest());

        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkElevacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteRepository.findAll().size();
        // set the field null
        lote.setElevacion(null);

        // Create the Lote, which fails.
        LoteDTO loteDTO = loteMapper.toDto(lote);


        restLoteMockMvc.perform(post("/api/lotes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteDTO)))
            .andExpect(status().isBadRequest());

        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLotes() throws Exception {
        // Initialize the database
        loteRepository.saveAndFlush(lote);

        // Get all the loteList
        restLoteMockMvc.perform(get("/api/lotes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lote.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].extension").value(hasItem(DEFAULT_EXTENSION.intValue())))
            .andExpect(jsonPath("$.[*].elevacion").value(hasItem(DEFAULT_ELEVACION.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getLote() throws Exception {
        // Initialize the database
        loteRepository.saveAndFlush(lote);

        // Get the lote
        restLoteMockMvc.perform(get("/api/lotes/{id}", lote.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lote.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.extension").value(DEFAULT_EXTENSION.intValue()))
            .andExpect(jsonPath("$.elevacion").value(DEFAULT_ELEVACION.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingLote() throws Exception {
        // Get the lote
        restLoteMockMvc.perform(get("/api/lotes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLote() throws Exception {
        // Initialize the database
        loteRepository.saveAndFlush(lote);

        int databaseSizeBeforeUpdate = loteRepository.findAll().size();

        // Update the lote
        Lote updatedLote = loteRepository.findById(lote.getId()).get();
        // Disconnect from session so that the updates on updatedLote are not directly saved in db
        em.detach(updatedLote);
        updatedLote
            .name(UPDATED_NAME)
            .extension(UPDATED_EXTENSION)
            .elevacion(UPDATED_ELEVACION)
            .description(UPDATED_DESCRIPTION);
        LoteDTO loteDTO = loteMapper.toDto(updatedLote);

        restLoteMockMvc.perform(put("/api/lotes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteDTO)))
            .andExpect(status().isOk());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeUpdate);
        Lote testLote = loteList.get(loteList.size() - 1);
        assertThat(testLote.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLote.getExtension()).isEqualTo(UPDATED_EXTENSION);
        assertThat(testLote.getElevacion()).isEqualTo(UPDATED_ELEVACION);
        assertThat(testLote.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingLote() throws Exception {
        int databaseSizeBeforeUpdate = loteRepository.findAll().size();

        // Create the Lote
        LoteDTO loteDTO = loteMapper.toDto(lote);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoteMockMvc.perform(put("/api/lotes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLote() throws Exception {
        // Initialize the database
        loteRepository.saveAndFlush(lote);

        int databaseSizeBeforeDelete = loteRepository.findAll().size();

        // Delete the lote
        restLoteMockMvc.perform(delete("/api/lotes/{id}", lote.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
