package com.jacinto.fas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jacinto.fas.web.rest.TestUtil;

public class FincaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FincaDTO.class);
        FincaDTO fincaDTO1 = new FincaDTO();
        fincaDTO1.setId(1L);
        FincaDTO fincaDTO2 = new FincaDTO();
        assertThat(fincaDTO1).isNotEqualTo(fincaDTO2);
        fincaDTO2.setId(fincaDTO1.getId());
        assertThat(fincaDTO1).isEqualTo(fincaDTO2);
        fincaDTO2.setId(2L);
        assertThat(fincaDTO1).isNotEqualTo(fincaDTO2);
        fincaDTO1.setId(null);
        assertThat(fincaDTO1).isNotEqualTo(fincaDTO2);
    }
}
