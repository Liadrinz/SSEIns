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
@Table(name = "filesse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filesse.findAll", query = "SELECT f FROM Filesse f")
    , @NamedQuery(name = "Filesse.findByFId", query = "SELECT f FROM Filesse f WHERE f.fId = :fId")
    , @NamedQuery(name = "Filesse.findByUrl", query = "SELECT f FROM Filesse f WHERE f.url = :url")
    , @NamedQuery(name = "Filesse.findByType", query = "SELECT f FROM Filesse f WHERE f.type = :type")
    , @NamedQuery(name = "Filesse.findAvatarBySId", query = "SELECT f FROM Filesse f WHERE f.inStudent = :sId")
    , @NamedQuery(name = "Filesse.findPhotoByNId", query = "SELECT f FROM Filesse f WHERE f.inNotice = :nId")
    , @NamedQuery(name = "Filesse.findPhotoByAlbId", query = "SELECT f FROM Filesse f WHERE f.inAlbum = :albId")})
public class Filesse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fId")
    private Integer fId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "url")
    private String url;
    @Size(max = 16)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "inAlbum", referencedColumnName = "albId")
    @ManyToOne
    private Album inAlbum;
    @JoinColumn(name = "inNotice", referencedColumnName = "nId")
    @ManyToOne
    private Notice inNotice;
    @JoinColumn(name = "inStudent", referencedColumnName = "sId")
    @ManyToOne
    private Student inStudent;

    public Filesse() {
    }

    public Filesse(Integer fId) {
        this.fId = fId;
    }

    public Filesse(Integer fId, String url) {
        this.fId = fId;
        this.url = url;
    }

    public Integer getFId() {
        return fId;
    }

    public void setFId(Integer fId) {
        this.fId = fId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Album getInAlbum() {
        return inAlbum;
    }

    public void setInAlbum(Album inAlbum) {
        this.inAlbum = inAlbum;
    }

    public Notice getInNotice() {
        return inNotice;
    }

    public void setInNotice(Notice inNotice) {
        this.inNotice = inNotice;
    }

    public Student getInStudent() {
        return inStudent;
    }

    public void setInStudent(Student inStudent) {
        this.inStudent = inStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fId != null ? fId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filesse)) {
            return false;
        }
        Filesse other = (Filesse) object;
        if ((this.fId == null && other.fId != null) || (this.fId != null && !this.fId.equals(other.fId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "monkey.Filesse[ fId=" + fId + " ]";
    }
    
}
