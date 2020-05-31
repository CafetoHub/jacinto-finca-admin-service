package com.jacinto.fas.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.jacinto.fas.domain.Finca} entity.
 */
public class FincaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer elevation;

    private String gps;

    
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

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FincaDTO)) {
            return false;
        }

        return id != null && id.equals(((FincaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FincaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", elevation=" + getElevation() +
            ", gps='" + getGps() + "'" +
            "}";
    }
}
