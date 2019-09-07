/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1;

import com.monkey.backends1.util.Encrypt;
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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findBySId", query = "SELECT s FROM Student s WHERE s.sId = :sId")
    , @NamedQuery(name = "Student.findByBuptId", query = "SELECT s FROM Student s WHERE s.buptId = :buptId")
    , @NamedQuery(name = "Student.findByName", query = "SELECT s FROM Student s WHERE s.name = :name")
    , @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email")
    , @NamedQuery(name = "Student.findBySex", query = "SELECT s FROM Student s WHERE s.sex = :sex")
    , @NamedQuery(name = "Student.findByStatus", query = "SELECT s FROM Student s WHERE s.status = :status")
    , @NamedQuery(name = "Student.findByPassword", query = "SELECT s FROM Student s WHERE s.password = :password")
    , @NamedQuery(name = "Student.findByBirthday", query = "SELECT s FROM Student s WHERE s.birthday = :birthday")
    , @NamedQuery(name = "Student.findBySalary", query = "SELECT s FROM Student s WHERE s.salary = :salary")
    , @NamedQuery(name = "Student.findByEthnic", query = "SELECT s FROM Student s WHERE s.ethnic = :ethnic")
    , @NamedQuery(name = "Student.findByRelationship", query = "SELECT s FROM Student s WHERE s.relationship = :relationship")
    , @NamedQuery(name = "Student.findByKlass", query = "SELECT s FROM Student s WHERE s.klass = :klass")
    , @NamedQuery(name = "Student.findByGrade", query = "SELECT s FROM Student s WHERE s.klass.grade = :grade")
    , @NamedQuery(name = "Student.findByKlassNum", query = "SELECT s FROM Student s WHERE s.klass.num = :num")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sId")
    private Integer sId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "buptId")
    private String buptId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="电子邮件无效")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    @Size(max = 8)
    @Column(name = "sex")
    private String sex;
    @Size(max = 64)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "salary")
    private Integer salary;
    @Size(max = 8)
    @Column(name = "ethnic")
    private String ethnic;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "company")
    private String company;
    @Size(max = 32)
    @Column(name = "relationship")
    private String relationship;
    @Size(max = 45)
    @Column(name = "location")
    private String location;
    @Lob
    @Size(max = 65535)
    @Column(name = "intro")
    private String intro;
    @OneToMany(mappedBy = "inStudent")
    private Collection<Filesse> filesseCollection;
    @JoinColumn(name = "klass", referencedColumnName = "kId")
    @ManyToOne(optional = false)
    private Klass klass;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commenter")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "publisher")
    private Collection<Notice> noticeCollection;

    public Student() {
    }

    public Student(Integer sId) {
        this.sId = sId;
    }

    public Student(Integer sId, String buptId, String name, String password) {
        this.sId = sId;
        this.buptId = buptId;
        this.name = name;
        this.password = password;
    }

    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public String getBuptId() {
        return buptId;
    }

    public void setBuptId(String buptId) {
        this.buptId = buptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        this.password = Encrypt.encrypt(password);
    }
    
    public boolean checkPassword(String password) throws Exception {
        return this.password.equals(Encrypt.encrypt(password));
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @XmlTransient
    public Collection<Filesse> getFilesseCollection() {
        return filesseCollection;
    }

    public void setFilesseCollection(Collection<Filesse> filesseCollection) {
        this.filesseCollection = filesseCollection;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Notice> getNoticeCollection() {
        return noticeCollection;
    }

    public void setNoticeCollection(Collection<Notice> noticeCollection) {
        this.noticeCollection = noticeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sId != null ? sId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.sId == null && other.sId != null) || (this.sId != null && !this.sId.equals(other.sId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "monkey.Student[ sId=" + sId + " ]";
    }
    
}
