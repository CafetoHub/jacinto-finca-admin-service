package com.jacinto.fas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Lote.
 */
@Entity
@Table(name = "lote")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "extension", nullable = false)
    private Long extension;

    @NotNull
    @Column(name = "elevacion", nullable = false)
    private Long elevacion;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties(value = "lotes", allowSetters = true)
    private Cultivo cultivo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Lote name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExtension() {
        return extension;
    }

    public Lote extension(Long extension) {
        this.extension = extension;
        return this;
    }

    public void setExtension(Long extension) {
        this.extension = extension;
    }

    public Long getElevacion() {
        return elevacion;
    }

    public Lote elevacion(Long elevacion) {
        this.elevacion = elevacion;
        return this;
    }

    public void setElevacion(Long elevacion) {
        this.elevacion = elevacion;
    }

    public String getDescription() {
        return description;
    }

    public Lote description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public Lote cultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
        return this;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lote)) {
            return false;
        }
        return id != null && id.equals(((Lote) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lote{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", extension=" + getExtension() +
            ", elevacion=" + getElevacion() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
