package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CertificadoMapperTest {

    private CertificadoMapper certificadoMapper;

    @BeforeEach
    public void setUp() {
        certificadoMapper = new CertificadoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(certificadoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(certificadoMapper.fromId(null)).isNull();
    }
}
