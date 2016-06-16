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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "solution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solution.findAll", query = "SELECT s FROM Solution s"),
    @NamedQuery(name = "Solution.findByIdsolution", query = "SELECT s FROM Solution s WHERE s.idsolution = :idsolution"),
    @NamedQuery(name = "Solution.findByTag", query = "SELECT s FROM Solution s WHERE s.tag = :tag"),
    @NamedQuery(name = "Solution.findByIduserdes", query = "SELECT s FROM Solution s WHERE s.iduserdes = :iduserdes"),
    @NamedQuery(name = "Solution.findByIduserorg", query = "SELECT s FROM Solution s WHERE s.iduserorg = :iduserorg"),
    @NamedQuery(name = "Solution.findByAnswer", query = "SELECT s FROM Solution s WHERE s.answer = :answer")})
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsolution")
    private Integer idsolution;
    @Size(max = 45)
    @Column(name = "tag")
    private String tag;
    @Column(name = "iduserdes")
    private Integer iduserdes;
    @Column(name = "iduserorg")
    private Integer iduserorg;
    @Size(max = 45)
    @Column(name = "answer")
    private String answer;
    @JoinColumn(name = "idexer", referencedColumnName = "idexercise")
    @ManyToOne
    private Exercise idexer;

    public Solution() {
    }

    public Solution(Integer idsolution) {
        this.idsolution = idsolution;
    }

    public Integer getIdsolution() {
        return idsolution;
    }

    public void setIdsolution(Integer idsolution) {
        this.idsolution = idsolution;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIduserdes() {
        return iduserdes;
    }

    public void setIduserdes(Integer iduserdes) {
        this.iduserdes = iduserdes;
    }

    public Integer getIduserorg() {
        return iduserorg;
    }

    public void setIduserorg(Integer iduserorg) {
        this.iduserorg = iduserorg;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Exercise getIdexer() {
        return idexer;
    }

    public void setIdexer(Exercise idexer) {
        this.idexer = idexer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsolution != null ? idsolution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solution)) {
            return false;
        }
        Solution other = (Solution) object;
        if ((this.idsolution == null && other.idsolution != null) || (this.idsolution != null && !this.idsolution.equals(other.idsolution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubi.sd.myshak.persistence.entity.Solution[ idsolution=" + idsolution + " ]";
    }
    
}
