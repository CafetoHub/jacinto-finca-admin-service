package com.jacinto.fas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class CultivoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cultivo.class);
        Cultivo cultivo1 = new Cultivo();
        cultivo1.setId(1L);
        Cultivo cultivo2 = new Cultivo();
        cultivo2.setId(cultivo1.getId());
        assertThat(cultivo1).isEqualTo(cultivo2);
        cultivo2.setId(2L);
        assertThat(cultivo1).isNotEqualTo(cultivo2);
        cultivo1.setId(null);
        assertThat(cultivo1).isNotEqualTo(cultivo2);
    }
}
