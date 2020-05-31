package com.jacinto.fas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class EntidadCertificadoraTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntidadCertificadora.class);
        EntidadCertificadora entidadCertificadora1 = new EntidadCertificadora();
        entidadCertificadora1.setId(1L);
        EntidadCertificadora entidadCertificadora2 = new EntidadCertificadora();
        entidadCertificadora2.setId(entidadCertificadora1.getId());
        assertThat(entidadCertificadora1).isEqualTo(entidadCertificadora2);
        entidadCertificadora2.setId(2L);
        assertThat(entidadCertificadora1).isNotEqualTo(entidadCertificadora2);
        entidadCertificadora1.setId(null);
        assertThat(entidadCertificadora1).isNotEqualTo(entidadCertificadora2);
    }
}
