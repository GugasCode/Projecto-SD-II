/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.persistencia.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.annotation.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ng
 * 
 * 
 * JPA Entity BEAN
 */

@Entity
@Table(name = "exercise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exercise.findAll", query = "SELECT e FROM Exercise e"),
    @NamedQuery(name = "Exercise.findByIdexercise", query = "SELECT e FROM Exercise e WHERE e.idexercise = :idexercise"),
    @NamedQuery(name = "Exercise.findByUc", query = "SELECT e FROM Exercise e WHERE e.uc = :uc"),
    @NamedQuery(name = "Exercise.findByTag", query = "SELECT e FROM Exercise e WHERE e.tag = :tag"),
    @NamedQuery(name = "Exercise.findByTitle", query = "SELECT e FROM Exercise e WHERE e.title = :title"),
    @NamedQuery(name = "Exercise.findByQuestion", query = "SELECT e FROM Exercise e WHERE e.question = :question"),
    @NamedQuery(name = "Exercise.findByTsolution", query = "SELECT e FROM Exercise e WHERE e.tsolution = :tsolution"),
    @NamedQuery(name = "Exercise.findByCheckb", query = "SELECT e FROM Exercise e WHERE e.checkb = :checkb")})
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idexercise")
    private Integer idexercise;
    @Size(max = 45)
    @Column(name = "uc")
    private String uc;
    @Size(max = 45)
    @Column(name = "tag")
    private String tag;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 45)
    @Column(name = "question")
    private String question;
    @Column(name = "tsolution")
    private Integer tsolution;
    @Size(max = 45)
    @Column(name = "checkb")
    private String checkb;
    @OneToMany(mappedBy = "idexer")
    private Collection<Solution> solutionCollection;

    public Exercise() {
    }

    public Exercise(Integer idexercise) {
        this.idexercise = idexercise;
    }

    public Integer getIdexercise() {
        return idexercise;
    }

    public void setIdexercise(Integer idexercise) {
        this.idexercise = idexercise;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getTsolution() {
        return tsolution;
    }

    public void setTsolution(Integer tsolution) {
        this.tsolution = tsolution;
    }

    public String getCheckb() {
        return checkb;
    }

    public void setCheckb(String checkb) {
        this.checkb = checkb;
    }

    @XmlTransient
    public Collection<Solution> getSolutionCollection() {
        return solutionCollection;
    }

    public void setSolutionCollection(Collection<Solution> solutionCollection) {
        this.solutionCollection = solutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexercise != null ? idexercise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.idexercise == null && other.idexercise != null) || (this.idexercise != null && !this.idexercise.equals(other.idexercise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubi.sd.myshak.persistence.entity.Exercise[ idexercise=" + idexercise + " ]";
    }
    
}
