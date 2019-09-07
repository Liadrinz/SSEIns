/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.stevenz.frontend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author StevenZ
 */

public class Student {
    private String name;
    private String grade;
    private String  klass;
    private String studentID;
    private String img;
    private String sex;

    public Student(){
        
    }
    
    public Student(String name,String grade,String klass,String studentID,String img,String sex){
        this.name = name;
        this.grade = grade;
        this.klass = klass;
        this.studentID = studentID;
        this.img = img;
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String Grade) {
        this.grade = Grade;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String Klass) {
        this.klass = Klass;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
  
    
}
