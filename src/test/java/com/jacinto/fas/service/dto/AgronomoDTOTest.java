package com.jacinto.fas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class AgronomoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgronomoDTO.class);
        AgronomoDTO agronomoDTO1 = new AgronomoDTO();
        agronomoDTO1.setId(1L);
        AgronomoDTO agronomoDTO2 = new AgronomoDTO();
        assertThat(agronomoDTO1).isNotEqualTo(agronomoDTO2);
        agronomoDTO2.setId(agronomoDTO1.getId());
        assertThat(agronomoDTO1).isEqualTo(agronomoDTO2);
        agronomoDTO2.setId(2L);
        assertThat(agronomoDTO1).isNotEqualTo(agronomoDTO2);
        agronomoDTO1.setId(null);
        assertThat(agronomoDTO1).isNotEqualTo(agronomoDTO2);
    }
}
