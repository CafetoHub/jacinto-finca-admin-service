package com.jacinto.fas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CertificadorMapperTest {

    private CertificadorMapper certificadorMapper;

    @BeforeEach
    public void setUp() {
        certificadorMapper = new CertificadorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(certificadorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(certificadorMapper.fromId(null)).isNull();
    }
}
