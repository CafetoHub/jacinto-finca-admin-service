package com.jacinto.fas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Certificado.
 */
@Entity
@Table(name = "certificado")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Certificado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "date_given", nullable = false)
    private String dateGiven;

    @ManyToOne
    @JsonIgnoreProperties(value = "certificados", allowSetters = true)
    private Agregado agregado;

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

    public Certificado name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateGiven() {
        return dateGiven;
    }

    public Certificado dateGiven(String dateGiven) {
        this.dateGiven = dateGiven;
        return this;
    }

    public void setDateGiven(String dateGiven) {
        this.dateGiven = dateGiven;
    }

    public Agregado getAgregado() {
        return agregado;
    }

    public Certificado agregado(Agregado agregado) {
        this.agregado = agregado;
        return this;
    }

    public void setAgregado(Agregado agregado) {
        this.agregado = agregado;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Certificado)) {
            return false;
        }
        return id != null && id.equals(((Certificado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Certificado{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", dateGiven='" + getDateGiven() + "'" +
            "}";
    }
}
