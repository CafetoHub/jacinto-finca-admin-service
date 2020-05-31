package com.jacinto.fas.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.jacinto.fas.domain.Certificado} entity.
 */
public class CertificadoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String dateGiven;


    private Long agregadoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateGiven() {
        return dateGiven;
    }

    public void setDateGiven(String dateGiven) {
        this.dateGiven = dateGiven;
    }

    public Long getAgregadoId() {
        return agregadoId;
    }

    public void setAgregadoId(Long agregadoId) {
        this.agregadoId = agregadoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CertificadoDTO)) {
            return false;
        }

        return id != null && id.equals(((CertificadoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CertificadoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", dateGiven='" + getDateGiven() + "'" +
            ", agregadoId=" + getAgregadoId() +
            "}";
    }
}
