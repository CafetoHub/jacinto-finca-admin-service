package com.jacinto.fas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class AgregadoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgregadoDTO.class);
        AgregadoDTO agregadoDTO1 = new AgregadoDTO();
        agregadoDTO1.setId(1L);
        AgregadoDTO agregadoDTO2 = new AgregadoDTO();
        assertThat(agregadoDTO1).isNotEqualTo(agregadoDTO2);
        agregadoDTO2.setId(agregadoDTO1.getId());
        assertThat(agregadoDTO1).isEqualTo(agregadoDTO2);
        agregadoDTO2.setId(2L);
        assertThat(agregadoDTO1).isNotEqualTo(agregadoDTO2);
        agregadoDTO1.setId(null);
        assertThat(agregadoDTO1).isNotEqualTo(agregadoDTO2);
    }
}
