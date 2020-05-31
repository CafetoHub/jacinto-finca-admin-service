package com.jacinto.fas.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.jacinto.fas.domain.Certificador} entity.
 */
public class CertificadorDTO implements Serializable {
    
    private Long id;


    private Long entidadCertificadoraId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntidadCertificadoraId() {
        return entidadCertificadoraId;
    }

    public void setEntidadCertificadoraId(Long entidadCertificadoraId) {
        this.entidadCertificadoraId = entidadCertificadoraId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CertificadorDTO)) {
            return false;
        }

        return id != null && id.equals(((CertificadorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CertificadorDTO{" +
            "id=" + getId() +
            ", entidadCertificadoraId=" + getEntidadCertificadoraId() +
            "}";
    }
}
