package com.jacinto.fas.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.jacinto.fas.domain.Lote} entity.
 */
public class LoteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long extension;

    @NotNull
    private Long elevacion;

    private String description;


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

    public Long getExtension() {
        return extension;
    }

    public void setExtension(Long extension) {
        this.extension = extension;
    }

    public Long getElevacion() {
        return elevacion;
    }

    public void setElevacion(Long elevacion) {
        this.elevacion = elevacion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof LoteDTO)) {
            return false;
        }

        return id != null && id.equals(((LoteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoteDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", extension=" + getExtension() +
            ", elevacion=" + getElevacion() +
            ", description='" + getDescription() + "'" +
            ", cultivoId=" + getCultivoId() +
            "}";
    }
}
