/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ahuan
 */
@Entity
@Table(name = "notice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notice.findAll", query = "SELECT n FROM Notice n")
    , @NamedQuery(name = "Notice.findByNId", query = "SELECT n FROM Notice n WHERE n.nId = :nId")
    , @NamedQuery(name = "Notice.findByPublishTime", query = "SELECT n FROM Notice n WHERE n.publishTime = :publishTime")})
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nId")
    private Integer nId;
    @Lob
    @Size(max = 65535)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime = new Date();
    @OneToMany(mappedBy = "inNotice")
    private Collection<Filesse> filesseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notice")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "publisher", referencedColumnName = "sId")
    @ManyToOne
    private Student publisher;

    public Notice() {
    }

    public Notice(Integer nId) {
        this.nId = nId;
    }

    public Notice(Integer nId, Date publishTime) {
        this.nId = nId;
        this.publishTime = publishTime;
    }

    public Integer getNId() {
        return nId;
    }

    public void setNId(Integer nId) {
        this.nId = nId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @XmlTransient
    public Collection<Filesse> getFilesseCollection() {
        return filesseCollection;
    }

    public void setFilesseCollection(Collection<Filesse> filesseCollection) {
        this.filesseCollection = filesseCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Student getPublisher() {
        return publisher;
    }

    public void setPublisher(Student publisher) {
        this.publisher = publisher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nId != null ? nId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notice)) {
            return false;
        }
        Notice other = (Notice) object;
        if ((this.nId == null && other.nId != null) || (this.nId != null && !this.nId.equals(other.nId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mon.Notice[ nId=" + nId + " ]";
    }
    
}
