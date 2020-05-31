package com.jacinto.fas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Agregado.
 */
@Entity
@Table(name = "agregado")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Agregado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "agregado")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Certificado> certificados = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "agregados", allowSetters = true)
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

    public Agregado cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Boolean isActive() {
        return active;
    }

    public Agregado active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Certificado> getCertificados() {
        return certificados;
    }

    public Agregado certificados(Set<Certificado> certificados) {
        this.certificados = certificados;
        return this;
    }

    public Agregado addCertificados(Certificado certificado) {
        this.certificados.add(certificado);
        certificado.setAgregado(this);
        return this;
    }

    public Agregado removeCertificados(Certificado certificado) {
        this.certificados.remove(certificado);
        certificado.setAgregado(null);
        return this;
    }

    public void setCertificados(Set<Certificado> certificados) {
        this.certificados = certificados;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public Agregado cultivo(Cultivo cultivo) {
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
        if (!(o instanceof Agregado)) {
            return false;
        }
        return id != null && id.equals(((Agregado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agregado{" +
            "id=" + getId() +
            ", cedula='" + getCedula() + "'" +
            ", active='" + isActive() + "'" +
            "}";
    }
}
