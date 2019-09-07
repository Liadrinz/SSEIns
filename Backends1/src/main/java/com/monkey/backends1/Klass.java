/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ahuan
 */
@Entity
@Table(name = "klass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klass.findAll", query = "SELECT k FROM Klass k")
    , @NamedQuery(name = "Klass.findByKId", query = "SELECT k FROM Klass k WHERE k.kId = :kId")
    , @NamedQuery(name = "Klass.findByGrade", query = "SELECT k FROM Klass k WHERE k.grade = :grade")
    , @NamedQuery(name = "Klass.findByNum", query = "SELECT k FROM Klass k WHERE k.num = :num")})
public class Klass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kId")
    private Integer kId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "grade")
    private String grade;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "num")
    private String num;
    @JoinTable(name = "klasses_albums", joinColumns = {
        @JoinColumn(name = "klassBelong", referencedColumnName = "kId")}, inverseJoinColumns = {
        @JoinColumn(name = "albumBelong", referencedColumnName = "albId")})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inKlass")
    private Collection<Album> albumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klass")
    private Collection<Student> studentCollection;

    public Klass() {
    }

    public Klass(Integer kId) {
        this.kId = kId;
    }

    public Klass(Integer kId, String grade, String num) {
        this.kId = kId;
        this.grade = grade;
        this.num = num;
    }

    public Integer getKId() {
        return kId;
    }

    public void setKId(Integer kId) {
        this.kId = kId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kId != null ? kId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klass)) {
            return false;
        }
        Klass other = (Klass) object;
        if ((this.kId == null && other.kId != null) || (this.kId != null && !this.kId.equals(other.kId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "monkey.Klass[ kId=" + kId + " ]";
    }
    
}
