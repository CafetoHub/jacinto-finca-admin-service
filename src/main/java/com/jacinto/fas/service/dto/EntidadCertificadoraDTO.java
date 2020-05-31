package com.jacinto.fas.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.jacinto.fas.domain.enumeration.TipoCertificadora;

/**
 * A DTO for the {@link com.jacinto.fas.domain.EntidadCertificadora} entity.
 */
public class EntidadCertificadoraDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private TipoCertificadora tipo;


    private Long cultivoId;
    
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

    public TipoCertificadora getTipo() {
        return tipo;
    }

    public void setTipo(TipoCertificadora tipo) {
        this.tipo = tipo;
    }

    public Long getCultivoId() {
        return cultivoId;
    }

    public void setCultivoId(Long cultivoId) {
        this.cultivoId = cultivoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntidadCertificadoraDTO)) {
            return false;
        }

        return id != null && id.equals(((EntidadCertificadoraDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntidadCertificadoraDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", cultivoId=" + getCultivoId() +
            "}";
    }
}
