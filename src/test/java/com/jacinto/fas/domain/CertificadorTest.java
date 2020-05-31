package com.jacinto.fas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class CertificadorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Certificador.class);
        Certificador certificador1 = new Certificador();
        certificador1.setId(1L);
        Certificador certificador2 = new Certificador();
        certificador2.setId(certificador1.getId());
        assertThat(certificador1).isEqualTo(certificador2);
        certificador2.setId(2L);
        assertThat(certificador1).isNotEqualTo(certificador2);
        certificador1.setId(null);
        assertThat(certificador1).isNotEqualTo(certificador2);
    }
}
