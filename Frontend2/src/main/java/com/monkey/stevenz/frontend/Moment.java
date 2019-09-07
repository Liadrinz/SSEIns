/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.stevenz.frontend;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author StevenZ
 */

public class Moment {
    
    private String name;
    private String content;
    private String date;
    private String img;
    private String momentID;

    public String getMomentID() {
        return momentID;
    }

    public void setMomentID(String momentID) {
        this.momentID = momentID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public Moment (){
        
    }
    
    public Moment(String name,String content,String date,String img,String momentid){
        this.name = name;
        this.content = content;
        this.date = date;
        this.img = img;
        this.momentID = momentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
