package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FincaMapperTest {

    private FincaMapper fincaMapper;

    @BeforeEach
    public void setUp() {
        fincaMapper = new FincaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fincaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fincaMapper.fromId(null)).isNull();
    }
}
