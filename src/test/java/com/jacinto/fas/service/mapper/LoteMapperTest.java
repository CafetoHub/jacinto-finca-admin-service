package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LoteMapperTest {

    private LoteMapper loteMapper;

    @BeforeEach
    public void setUp() {
        loteMapper = new LoteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(loteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(loteMapper.fromId(null)).isNull();
    }
}
