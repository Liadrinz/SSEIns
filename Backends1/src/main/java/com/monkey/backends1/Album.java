/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "album")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a")
    , @NamedQuery(name = "Album.findByAlbId", query = "SELECT a FROM Album a WHERE a.albId = :albId")
    , @NamedQuery(name = "Album.findByAlbTitle", query = "SELECT a FROM Album a WHERE a.albTitle = :albTitle")
    , @NamedQuery(name = "Album.findByGrade", query = "SELECT a FROM Album a WHERE a.grade = :grade")
    , @NamedQuery(name = "Album.findAcademyAlbum", query = "SELECT a FROM Album a WHERE a.inKlass IS NULL AND (a.grade IS NULL OR a.grade=\"\")")
    , @NamedQuery(name = "Album.findKlassAlbum", query = "SELECT a FROM Album a WHERE a.inKlass = :klass")
    , @NamedQuery(name = "Album.findGradeAlbum", query = "SELECT a FROM Album a WHERE a.grade = :grade")})
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "albId")
    private Integer albId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "albTitle")
    private String albTitle;
    @Lob
    @Size(max = 65535)
    @Column(name = "albIntro")
    private String albIntro;
    @JoinColumn(name = "inKlass", referencedColumnName = "kId")
    @ManyToOne
    private Klass inKlass;
    @OneToMany(mappedBy = "inAlbum")
    private Collection<Filesse> filesseCollection;
    @Basic(optional = false)
    @Size(min = 0, max = 54)
    @Column(name = "grade")
    private String grade;
    

    public Album() {
    }

    public Album(Integer albId) {
        this.albId = albId;
    }

    public Album(Integer albId, String albTitle) {
        this.albId = albId;
        this.albTitle = albTitle;
    }

    public Integer getAlbId() {
        return albId;
    }

    public void setAlbId(Integer albId) {
        this.albId = albId;
    }

    public String getAlbTitle() {
        return albTitle;
    }

    public void setAlbTitle(String albTitle) {
        this.albTitle = albTitle;
    }

    public String getAlbIntro() {
        return albIntro;
    }

    public void setAlbIntro(String albIntro) {
        this.albIntro = albIntro;
    }

    public Klass getInKlass() {
        return inKlass;
    }

    public void setInKlass(Klass inKlass) {
        this.inKlass = inKlass;
    }

    @XmlTransient
    public Collection<Filesse> getFilesseCollection() {
        return filesseCollection;
    }

    public void setFilesseCollection(Collection<Filesse> filesseCollection) {
        this.filesseCollection = filesseCollection;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albId != null ? albId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.albId == null && other.albId != null) || (this.albId != null && !this.albId.equals(other.albId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "monkey.Album[ albId=" + albId + " ]";
    }
    
}
