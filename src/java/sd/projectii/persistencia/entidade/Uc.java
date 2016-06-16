/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.persistencia.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ng
 */
@Entity
@Table(name = "uc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uc.findAll", query = "SELECT u FROM Uc u"),
    @NamedQuery(name = "Uc.findByIduc", query = "SELECT u FROM Uc u WHERE u.iduc = :iduc"),
    @NamedQuery(name = "Uc.findByName", query = "SELECT u FROM Uc u WHERE u.name = :name")})
public class Uc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduc")
    private Integer iduc;
    @Size(max = 45)
    @Column(name = "name")
    private String name;

    public Uc() {
    }

    public Uc(Integer iduc) {
        this.iduc = iduc;
    }

    public Integer getIduc() {
        return iduc;
    }

    public void setIduc(Integer iduc) {
        this.iduc = iduc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduc != null ? iduc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uc)) {
            return false;
        }
        Uc other = (Uc) object;
        if ((this.iduc == null && other.iduc != null) || (this.iduc != null && !this.iduc.equals(other.iduc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubi.sd.myshak.persistence.entity.Uc[ iduc=" + iduc + " ]";
    }
    
}
