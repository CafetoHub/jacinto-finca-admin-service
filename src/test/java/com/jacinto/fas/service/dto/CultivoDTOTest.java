package com.jacinto.fas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class CultivoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CultivoDTO.class);
        CultivoDTO cultivoDTO1 = new CultivoDTO();
        cultivoDTO1.setId(1L);
        CultivoDTO cultivoDTO2 = new CultivoDTO();
        assertThat(cultivoDTO1).isNotEqualTo(cultivoDTO2);
        cultivoDTO2.setId(cultivoDTO1.getId());
        assertThat(cultivoDTO1).isEqualTo(cultivoDTO2);
        cultivoDTO2.setId(2L);
        assertThat(cultivoDTO1).isNotEqualTo(cultivoDTO2);
        cultivoDTO1.setId(null);
        assertThat(cultivoDTO1).isNotEqualTo(cultivoDTO2);
    }
}
