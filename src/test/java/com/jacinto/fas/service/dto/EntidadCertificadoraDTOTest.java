package com.jacinto.fas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class EntidadCertificadoraDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntidadCertificadoraDTO.class);
        EntidadCertificadoraDTO entidadCertificadoraDTO1 = new EntidadCertificadoraDTO();
        entidadCertificadoraDTO1.setId(1L);
        EntidadCertificadoraDTO entidadCertificadoraDTO2 = new EntidadCertificadoraDTO();
        assertThat(entidadCertificadoraDTO1).isNotEqualTo(entidadCertificadoraDTO2);
        entidadCertificadoraDTO2.setId(entidadCertificadoraDTO1.getId());
        assertThat(entidadCertificadoraDTO1).isEqualTo(entidadCertificadoraDTO2);
        entidadCertificadoraDTO2.setId(2L);
        assertThat(entidadCertificadoraDTO1).isNotEqualTo(entidadCertificadoraDTO2);
        entidadCertificadoraDTO1.setId(null);
        assertThat(entidadCertificadoraDTO1).isNotEqualTo(entidadCertificadoraDTO2);
    }
}
