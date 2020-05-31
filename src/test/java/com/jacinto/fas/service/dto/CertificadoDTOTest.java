package com.jacinto.fas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class CertificadoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificadoDTO.class);
        CertificadoDTO certificadoDTO1 = new CertificadoDTO();
        certificadoDTO1.setId(1L);
        CertificadoDTO certificadoDTO2 = new CertificadoDTO();
        assertThat(certificadoDTO1).isNotEqualTo(certificadoDTO2);
        certificadoDTO2.setId(certificadoDTO1.getId());
        assertThat(certificadoDTO1).isEqualTo(certificadoDTO2);
        certificadoDTO2.setId(2L);
        assertThat(certificadoDTO1).isNotEqualTo(certificadoDTO2);
        certificadoDTO1.setId(null);
        assertThat(certificadoDTO1).isNotEqualTo(certificadoDTO2);
    }
}
