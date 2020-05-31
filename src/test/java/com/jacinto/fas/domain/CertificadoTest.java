package com.jacinto.fas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class CertificadoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Certificado.class);
        Certificado certificado1 = new Certificado();
        certificado1.setId(1L);
        Certificado certificado2 = new Certificado();
        certificado2.setId(certificado1.getId());
        assertThat(certificado1).isEqualTo(certificado2);
        certificado2.setId(2L);
        assertThat(certificado1).isNotEqualTo(certificado2);
        certificado1.setId(null);
        assertThat(certificado1).isNotEqualTo(certificado2);
    }
}
