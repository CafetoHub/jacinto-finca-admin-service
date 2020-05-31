package com.jacinto.fas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class CertificadorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificadorDTO.class);
        CertificadorDTO certificadorDTO1 = new CertificadorDTO();
        certificadorDTO1.setId(1L);
        CertificadorDTO certificadorDTO2 = new CertificadorDTO();
        assertThat(certificadorDTO1).isNotEqualTo(certificadorDTO2);
        certificadorDTO2.setId(certificadorDTO1.getId());
        assertThat(certificadorDTO1).isEqualTo(certificadorDTO2);
        certificadorDTO2.setId(2L);
        assertThat(certificadorDTO1).isNotEqualTo(certificadorDTO2);
        certificadorDTO1.setId(null);
        assertThat(certificadorDTO1).isNotEqualTo(certificadorDTO2);
    }
}
