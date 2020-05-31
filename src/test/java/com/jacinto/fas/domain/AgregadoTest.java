package com.jacinto.fas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class AgregadoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Agregado.class);
        Agregado agregado1 = new Agregado();
        agregado1.setId(1L);
        Agregado agregado2 = new Agregado();
        agregado2.setId(agregado1.getId());
        assertThat(agregado1).isEqualTo(agregado2);
        agregado2.setId(2L);
        assertThat(agregado1).isNotEqualTo(agregado2);
        agregado1.setId(null);
        assertThat(agregado1).isNotEqualTo(agregado2);
    }
}
