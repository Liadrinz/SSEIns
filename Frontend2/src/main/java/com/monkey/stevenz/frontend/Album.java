/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.stevenz.frontend;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 *
 * @author StevenZ
 */


public class Album {

    private String albumID;
    private String firstphoto;
    private String albumname;
    private String classID;
    private String grade;


    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

  

    public String getAlbumID() {
        return albumID;
    }

    public Album() {

    }

    public Album(String id, String name, String firstp) {
        this.albumID = id;
        this.albumname = name;
        this.firstphoto = firstp;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getFirstphoto() {
        return firstphoto;
    }

    public void setFirstphoto(String firstphoto) {
        this.firstphoto = firstphoto;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

   

    

    
    
}


