package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CultivoMapperTest {

    private CultivoMapper cultivoMapper;

    @BeforeEach
    public void setUp() {
        cultivoMapper = new CultivoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cultivoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cultivoMapper.fromId(null)).isNull();
    }
}
