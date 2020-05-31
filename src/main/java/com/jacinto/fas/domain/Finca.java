package com.jacinto.fas.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Finca.
 */
@Entity
@Table(name = "finca")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Finca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "elevation", nullable = false)
    private Integer elevation;

    @Column(name = "gps")
    private String gps;

    @OneToMany(mappedBy = "finca")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Cultivo> cultivos = new HashSet<>();

    @OneToMany(mappedBy = "finca")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Owner> owners = new HashSet<>();

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

    public Finca name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElevation() {
        return elevation;
    }

    public Finca elevation(Integer elevation) {
        this.elevation = elevation;
        return this;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public String getGps() {
        return gps;
    }

    public Finca gps(String gps) {
        this.gps = gps;
        return this;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Set<Cultivo> getCultivos() {
        return cultivos;
    }

    public Finca cultivos(Set<Cultivo> cultivos) {
        this.cultivos = cultivos;
        return this;
    }

    public Finca addCultivos(Cultivo cultivo) {
        this.cultivos.add(cultivo);
        cultivo.setFinca(this);
        return this;
    }

    public Finca removeCultivos(Cultivo cultivo) {
        this.cultivos.remove(cultivo);
        cultivo.setFinca(null);
        return this;
    }

    public void setCultivos(Set<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public Finca owners(Set<Owner> owners) {
        this.owners = owners;
        return this;
    }

    public Finca addOwners(Owner owner) {
        this.owners.add(owner);
        owner.setFinca(this);
        return this;
    }

    public Finca removeOwners(Owner owner) {
        this.owners.remove(owner);
        owner.setFinca(null);
        return this;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Finca)) {
            return false;
        }
        return id != null && id.equals(((Finca) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Finca{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", elevation=" + getElevation() +
            ", gps='" + getGps() + "'" +
            "}";
    }
}
