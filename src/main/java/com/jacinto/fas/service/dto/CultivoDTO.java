package com.jacinto.fas.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.jacinto.fas.domain.enumeration.TipoCultivo;

/**
 * A DTO for the {@link com.jacinto.fas.domain.Cultivo} entity.
 */
public class CultivoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private TipoCultivo tipo;


    private Long fincaId;
    
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

    public TipoCultivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoCultivo tipo) {
        this.tipo = tipo;
    }

    public Long getFincaId() {
        return fincaId;
    }

    public void setFincaId(Long fincaId) {
        this.fincaId = fincaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CultivoDTO)) {
            return false;
        }

        return id != null && id.equals(((CultivoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CultivoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", fincaId=" + getFincaId() +
            "}";
    }
}
