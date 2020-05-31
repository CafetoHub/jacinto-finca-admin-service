package com.jacinto.fas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.jacinto.fas.domain.enumeration.TipoCertificadora;

/**
 * A EntidadCertificadora.
 */
@Entity
@Table(name = "entidad_certificadora")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EntidadCertificadora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoCertificadora tipo;

    @OneToMany(mappedBy = "entidadCertificadora")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Certificador> certificadores = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "certificadoras", allowSetters = true)
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

    public EntidadCertificadora name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoCertificadora getTipo() {
        return tipo;
    }

    public EntidadCertificadora tipo(TipoCertificadora tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoCertificadora tipo) {
        this.tipo = tipo;
    }

    public Set<Certificador> getCertificadores() {
        return certificadores;
    }

    public EntidadCertificadora certificadores(Set<Certificador> certificadors) {
        this.certificadores = certificadors;
        return this;
    }

    public EntidadCertificadora addCertificadores(Certificador certificador) {
        this.certificadores.add(certificador);
        certificador.setEntidadCertificadora(this);
        return this;
    }

    public EntidadCertificadora removeCertificadores(Certificador certificador) {
        this.certificadores.remove(certificador);
        certificador.setEntidadCertificadora(null);
        return this;
    }

    public void setCertificadores(Set<Certificador> certificadors) {
        this.certificadores = certificadors;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public EntidadCertificadora cultivo(Cultivo cultivo) {
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
        if (!(o instanceof EntidadCertificadora)) {
            return false;
        }
        return id != null && id.equals(((EntidadCertificadora) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntidadCertificadora{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
