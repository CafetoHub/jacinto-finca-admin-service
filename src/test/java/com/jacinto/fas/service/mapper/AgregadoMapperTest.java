package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AgregadoMapperTest {

    private AgregadoMapper agregadoMapper;

    @BeforeEach
    public void setUp() {
        agregadoMapper = new AgregadoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(agregadoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(agregadoMapper.fromId(null)).isNull();
    }
}
