package com.jacinto.fas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Agronomo.
 */
@Entity
@Table(name = "agronomo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Agronomo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "cedula_profesional")
    private String cedulaProfesional;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JsonIgnoreProperties(value = "agronomos", allowSetters = true)
    private Cultivo cultivo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public Agronomo cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public Agronomo cedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
        return this;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public Boolean isActive() {
        return active;
    }

    public Agronomo active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public Agronomo cultivo(Cultivo cultivo) {
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
        if (!(o instanceof Agronomo)) {
            return false;
        }
        return id != null && id.equals(((Agronomo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agronomo{" +
            "id=" + getId() +
            ", cedula='" + getCedula() + "'" +
            ", cedulaProfesional='" + getCedulaProfesional() + "'" +
            ", active='" + isActive() + "'" +
            "}";
    }
}
