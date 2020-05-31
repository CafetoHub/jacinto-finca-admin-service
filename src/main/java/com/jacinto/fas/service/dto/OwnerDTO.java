package com.jacinto.fas.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.jacinto.fas.domain.Owner} entity.
 */
public class OwnerDTO implements Serializable {
    
    private Long id;

    private String cedula;


    private Long fincaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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
        if (!(o instanceof OwnerDTO)) {
            return false;
        }

        return id != null && id.equals(((OwnerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OwnerDTO{" +
            "id=" + getId() +
            ", cedula='" + getCedula() + "'" +
            ", fincaId=" + getFincaId() +
            "}";
    }
}
