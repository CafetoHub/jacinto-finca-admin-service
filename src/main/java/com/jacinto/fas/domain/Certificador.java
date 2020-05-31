package com.jacinto.fas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Certificador.
 */
@Entity
@Table(name = "certificador")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Certificador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "certificadores", allowSetters = true)
    private EntidadCertificadora entidadCertificadora;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntidadCertificadora getEntidadCertificadora() {
        return entidadCertificadora;
    }

    public Certificador entidadCertificadora(EntidadCertificadora entidadCertificadora) {
        this.entidadCertificadora = entidadCertificadora;
        return this;
    }

    public void setEntidadCertificadora(EntidadCertificadora entidadCertificadora) {
        this.entidadCertificadora = entidadCertificadora;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Certificador)) {
            return false;
        }
        return id != null && id.equals(((Certificador) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Certificador{" +
            "id=" + getId() +
            "}";
    }
}
