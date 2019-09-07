/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.hxl.enit;

import com.monkey.hxl.enit.Comment;
import java.util.List;

/**
 *
 * @author ahuan
 */
public class Notice {

    protected String nid;
    protected String pubname;
    protected String pubdate;
    protected String pubtext;
    public String imglist1="";
    public String imglist2="";
    public String imglist3="";
    public String imglist4="";

    public String getImglist1() {
        return imglist1;
    }

    public void setImglist1(String imglist1) {
        this.imglist1 = imglist1;
    }

    public String getImglist2() {
        return imglist2;
    }

    public void setImglist2(String imglist2) {
        this.imglist2 = imglist2;
    }

    public String getImglist3() {
        return imglist3;
    }

    public void setImglist3(String imglist3) {
        this.imglist3 = imglist3;
    }

    public String getImglist4() {
        return imglist4;
    }

    public void setImglist4(String imglist4) {
        this.imglist4 = imglist4;
    }

    public static String url = "http://10.128.216.20:8080";
    public String head;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
    
    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Notice.url = url;
    }
    protected List<Comment> comments = null;

    public Notice() {
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPubtext() {
        return pubtext;
    }

    public void setPubtext(String pubtext) {
        this.pubtext = pubtext;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

}
