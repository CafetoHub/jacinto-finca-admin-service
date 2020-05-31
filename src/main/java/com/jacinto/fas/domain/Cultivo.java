package com.jacinto.fas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.jacinto.fas.domain.enumeration.TipoCultivo;

/**
 * A Cultivo.
 */
@Entity
@Table(name = "cultivo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cultivo implements Serializable {

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
    private TipoCultivo tipo;

    @OneToMany(mappedBy = "cultivo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Agronomo> agronomos = new HashSet<>();

    @OneToMany(mappedBy = "cultivo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Agregado> agregados = new HashSet<>();

    @OneToMany(mappedBy = "cultivo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Lote> lotes = new HashSet<>();

    @OneToMany(mappedBy = "cultivo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<EntidadCertificadora> certificadoras = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "cultivos", allowSetters = true)
    private Finca finca;

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

    public Cultivo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoCultivo getTipo() {
        return tipo;
    }

    public Cultivo tipo(TipoCultivo tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoCultivo tipo) {
        this.tipo = tipo;
    }

    public Set<Agronomo> getAgronomos() {
        return agronomos;
    }

    public Cultivo agronomos(Set<Agronomo> agronomos) {
        this.agronomos = agronomos;
        return this;
    }

    public Cultivo addAgronomos(Agronomo agronomo) {
        this.agronomos.add(agronomo);
        agronomo.setCultivo(this);
        return this;
    }

    public Cultivo removeAgronomos(Agronomo agronomo) {
        this.agronomos.remove(agronomo);
        agronomo.setCultivo(null);
        return this;
    }

    public void setAgronomos(Set<Agronomo> agronomos) {
        this.agronomos = agronomos;
    }

    public Set<Agregado> getAgregados() {
        return agregados;
    }

    public Cultivo agregados(Set<Agregado> agregados) {
        this.agregados = agregados;
        return this;
    }

    public Cultivo addAgregados(Agregado agregado) {
        this.agregados.add(agregado);
        agregado.setCultivo(this);
        return this;
    }

    public Cultivo removeAgregados(Agregado agregado) {
        this.agregados.remove(agregado);
        agregado.setCultivo(null);
        return this;
    }

    public void setAgregados(Set<Agregado> agregados) {
        this.agregados = agregados;
    }

    public Set<Lote> getLotes() {
        return lotes;
    }

    public Cultivo lotes(Set<Lote> lotes) {
        this.lotes = lotes;
        return this;
    }

    public Cultivo addLotes(Lote lote) {
        this.lotes.add(lote);
        lote.setCultivo(this);
        return this;
    }

    public Cultivo removeLotes(Lote lote) {
        this.lotes.remove(lote);
        lote.setCultivo(null);
        return this;
    }

    public void setLotes(Set<Lote> lotes) {
        this.lotes = lotes;
    }

    public Set<EntidadCertificadora> getCertificadoras() {
        return certificadoras;
    }

    public Cultivo certificadoras(Set<EntidadCertificadora> entidadCertificadoras) {
        this.certificadoras = entidadCertificadoras;
        return this;
    }

    public Cultivo addCertificadoras(EntidadCertificadora entidadCertificadora) {
        this.certificadoras.add(entidadCertificadora);
        entidadCertificadora.setCultivo(this);
        return this;
    }

    public Cultivo removeCertificadoras(EntidadCertificadora entidadCertificadora) {
        this.certificadoras.remove(entidadCertificadora);
        entidadCertificadora.setCultivo(null);
        return this;
    }

    public void setCertificadoras(Set<EntidadCertificadora> entidadCertificadoras) {
        this.certificadoras = entidadCertificadoras;
    }

    public Finca getFinca() {
        return finca;
    }

    public Cultivo finca(Finca finca) {
        this.finca = finca;
        return this;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cultivo)) {
            return false;
        }
        return id != null && id.equals(((Cultivo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cultivo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
