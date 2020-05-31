package com.jacinto.fas.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.jacinto.fas.domain.Agronomo} entity.
 */
public class AgronomoDTO implements Serializable {
    
    private Long id;

    private String cedula;

    private String cedulaProfesional;

    private Boolean active;


    private Long cultivoId;
    
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

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        if (!(o instanceof AgronomoDTO)) {
            return false;
        }

        return id != null && id.equals(((AgronomoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgronomoDTO{" +
            "id=" + getId() +
            ", cedula='" + getCedula() + "'" +
            ", cedulaProfesional='" + getCedulaProfesional() + "'" +
            ", active='" + isActive() + "'" +
            ", cultivoId=" + getCultivoId() +
            "}";
    }
}
