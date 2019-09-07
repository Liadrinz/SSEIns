/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 李阿俊
 */
@Entity
@Table(name = "token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t")
    , @NamedQuery(name = "Token.findByTkId", query = "SELECT t FROM Token t WHERE t.tkId = :tkId")
    , @NamedQuery(name = "Token.findByTime", query = "SELECT t FROM Token t WHERE t.time = :time")
    , @NamedQuery(name = "Token.findByToken", query = "SELECT t FROM Token t WHERE t.token = :token")
    , @NamedQuery(name = "Token.findByStudent", query = "SELECT t FROM Token t WHERE t.reqStudent = :student")})
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tkId")
    private Integer tkId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "token")
    private String token;
    @JoinColumn(name = "reqStudent", referencedColumnName = "sId")
    @ManyToOne
    private Student reqStudent;

    public Token() {
    }

    public Token(Integer tkId) {
        this.tkId = tkId;
    }

    public Token(Integer tkId, Date time, String token) {
        this.tkId = tkId;
        this.time = time;
        this.token = token;
    }

    public Integer getTkId() {
        return tkId;
    }

    public void setTkId(Integer tkId) {
        this.tkId = tkId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Student getReqStudent() {
        return reqStudent;
    }

    public void setReqStudent(Student reqStudent) {
        this.reqStudent = reqStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tkId != null ? tkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.tkId == null && other.tkId != null) || (this.tkId != null && !this.tkId.equals(other.tkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monkey.backends1.Token[ tkId=" + tkId + " ]";
    }
    
}
