package com.jacinto.fas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class AgronomoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Agronomo.class);
        Agronomo agronomo1 = new Agronomo();
        agronomo1.setId(1L);
        Agronomo agronomo2 = new Agronomo();
        agronomo2.setId(agronomo1.getId());
        assertThat(agronomo1).isEqualTo(agronomo2);
        agronomo2.setId(2L);
        assertThat(agronomo1).isNotEqualTo(agronomo2);
        agronomo1.setId(null);
        assertThat(agronomo1).isNotEqualTo(agronomo2);
    }
}
