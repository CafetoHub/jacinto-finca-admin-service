package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AgronomoMapperTest {

    private AgronomoMapper agronomoMapper;

    @BeforeEach
    public void setUp() {
        agronomoMapper = new AgronomoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(agronomoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(agronomoMapper.fromId(null)).isNull();
    }
}
