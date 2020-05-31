package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EntidadCertificadoraMapperTest {

    private EntidadCertificadoraMapper entidadCertificadoraMapper;

    @BeforeEach
    public void setUp() {
        entidadCertificadoraMapper = new EntidadCertificadoraMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(entidadCertificadoraMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entidadCertificadoraMapper.fromId(null)).isNull();
    }
}
