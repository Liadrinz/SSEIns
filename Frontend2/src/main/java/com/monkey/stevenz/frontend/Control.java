/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.stevenz.frontend;

import com.monkey.liadrinz.frontend.login.LoginBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author StevenZ
 */
@Named("col")
@ViewScoped
public class Control implements Serializable {

    @EJB
    protected LoginBean loginbean;
    protected Integer aSId;

    public String searchurlS() {
        return "./searchresults-student.xhtml?keyword=" + this.getParameter("keyword");
    }

    public String searchurlM() {
        return "./searchresults-moment.xhtml?keyword=" + this.getParameter("keyword");
    }

    public Integer getaSId() {
        this.aSId = loginbean.getsId();
        return aSId;
    }

    public void setaSId(Integer aSId) {
        this.aSId = aSId;
    }

    private Album alb;
    static String URL = "http://10.128.216.20:8080";

    public Control() {
        this.alb = new Album();
    }

    public Album getAlb() {
        return alb;
    }

    public void setAlb(Album alb) {
        this.alb = alb;
    }
//获取班级相册

    public List<Album> getMyAlbum() {
        List<Album> a = new ArrayList<Album>();
        Document doc = null;

        try {
            Client client = ClientBuilder.newClient();
            String x =(this.getKG())[0];
            WebTarget target = client.target(URL + "/Backends1/rest-api/album/klass/"+(this.getKG())[0]);
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator("album");
            while (iter.hasNext()) {
                Album album = new Album();
                Element e = (Element) iter.next();
                album.setAlbumID(e.elementTextTrim("albId"));
                try {
                    Client client1 = ClientBuilder.newClient();
                    WebTarget target1 = client1.target(URL + "/Backends1/rest-api/filesse/album/" + album.getAlbumID());
                    String response1 = target1.request().get(String.class);
                    doc = DocumentHelper.parseText(response1);
                    Element root1 = doc.getRootElement();
                    Iterator iter1 = root1.elementIterator("filesse");
                    if (iter1.hasNext()) {
                        Element e1 = (Element) iter1.next();
                        album.setFirstphoto(URL+e1.elementTextTrim("url"));

                    }
                } catch (DocumentException ex) {

                }
                if (album.getFirstphoto() == null) {
                    album.setFirstphoto("./resources/images/pic-none.png");
                }
                album.setAlbumname(e.elementTextTrim("albTitle"));

                a.add(album);
            }
        } catch (DocumentException ex) {
            System.out.println("ERROR");
        }

        return a;
    }

//获取年级相册
    public List<Album> getGradeAlbum() {
        List<Album> a = new ArrayList<Album>();
        Document doc = null;

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "/Backends1/rest-api/album/grade/"+(this.getKG())[1]);
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator("album");
            while (iter.hasNext()) {
                Album album = new Album();
                Element e = (Element) iter.next();
                album.setAlbumID(e.elementTextTrim("albId"));
                try {
                    Client client1 = ClientBuilder.newClient();
                    WebTarget target1 = client1.target(URL + "/Backends1/rest-api/filesse/album/" + album.getAlbumID());
                    String response1 = target1.request().get(String.class);
                    doc = DocumentHelper.parseText(response1);
                    Element root1 = doc.getRootElement();
                    Iterator iter1 = root1.elementIterator("filesse");
                    if (iter1.hasNext()) {
                        Element e1 = (Element) iter1.next();
                        album.setFirstphoto(URL+e1.elementTextTrim("url"));
                    }
                } catch (DocumentException ex) {

                }
                album.setAlbumname(e.elementTextTrim("albTitle"));
                if (album.getFirstphoto() == null) {
                    album.setFirstphoto("./resources/images/pic-none.png");
                }

                a.add(album);
            }
        } catch (DocumentException ex) {
            System.out.println("ERROR");
        }

        return a;
    }
//获取学院相册

    public List<Album> getAcademyAlbum() {
        List<Album> a = new ArrayList<Album>();
        Document doc = null;

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "/Backends1/rest-api/album/academy");
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator("album");
            while (iter.hasNext()) {
                Album album = new Album();
                Element e = (Element) iter.next();
                album.setAlbumID(e.elementTextTrim("albId"));
                try {
                    Client client1 = ClientBuilder.newClient();
                    WebTarget target1 = client1.target(URL + "/Backends1/rest-api/filesse/album/" + album.getAlbumID());
                    String response1 = target1.request().get(String.class);
                    doc = DocumentHelper.parseText(response1);
                    Element root1 = doc.getRootElement();
                    Iterator iter1 = root1.elementIterator("filesse");
                    if (iter1.hasNext()) {
                        Element e1 = (Element) iter1.next();
                        album.setFirstphoto(URL+e1.elementTextTrim("url"));

                    }
                } catch (DocumentException ex) {

                }
                album.setAlbumname(e.elementTextTrim("albTitle"));
                if (album.getFirstphoto() == null) {
                    album.setFirstphoto("./resources/images/pic-none.png");
                }

                a.add(album);
            }
        } catch (DocumentException ex) {
            System.out.println("ERROR");
        }

        return a;
    }

    public String[] getKG() {
        String sid = String.valueOf(this.getaSId());
        Document doc = null;
        String[] a = new String[2];
        try {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "/Backends1/rest-api/student/" + sid);
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
          Element e= root.element("klass");
                 a[0] = e.elementTextTrim("KId");
                a[1] = e.elementTextTrim("grade");
            
            
                
               

            
        } catch (Exception e) {

        }
        return a;
    }

//获取相册图片
    public List<Photo> getAllPhoto() {
        List<Photo> photourl = new ArrayList<>();
        String albumID = getParameter("albumID");
        Document doc = null;

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "/Backends1/rest-api/filesse/album/" + albumID);
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator("filesse");
            while (iter.hasNext()) {
                Element e = (Element) iter.next();
                Photo photo = new Photo();
                photo.setAlbumID(albumID);
                photo.setPhotourl(URL+e.elementTextTrim("url"));
                photourl.add(photo);
            }

        } catch (Exception ex) {

        }
        return photourl;
    }
//开启相册

    public String openAlbum() {

        return "./albumcontent.xhtml?albumID=";
    }
//获取页面传参

    public String getParameter(String para) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getParameter(para);
    }
//     搜索学生

    public List<Student> searchStudent() {

        List<Student> result = new ArrayList<>();
        Document doc = null;
        Document doc1 = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "/Backends1/rest-api/student/search/?keywords=" + this.getParameter("keyword"));
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator("student");
            while (iter.hasNext()) {
                Element e = (Element) iter.next();
                Student stu = new Student();
                stu.setName(e.elementTextTrim("name"));
                stu.setStudentID(e.elementTextTrim("SId"));
                stu.setSex(e.elementTextTrim("sex"));
                Element e1 = e.element("klass");
                stu.setKlass(e1.elementTextTrim("num"));
                stu.setGrade(e1.elementTextTrim("grade"));
                //二次请求获得头像
                Client client1 = ClientBuilder.newClient();
                WebTarget target1 = client1.target(URL + "/Backends1/rest-api/filesse/student/" + stu.getStudentID());
                String response1 = target1.request().get(String.class);
                doc1 = DocumentHelper.parseText(response1);
                Element root1 = doc1.getRootElement();
                Iterator iter1 = root1.elementIterator("filesse");
                Element e2 = null;
                while (iter1.hasNext()) {

                    e2 = (Element) iter1.next();
                }

                if (e2 == null) {

                    stu.setImg("./resources/image/dev1ce.jpg");
                } else {
                    stu.setImg(URL+e2.elementTextTrim("url"));
                }
                result.add(stu);
            }
        } catch (DocumentException e) {

        } 
        return result;

    }
//     搜索动态

 public List<Moment> searchMoment() {
        List<Moment> result = new ArrayList<>();
        Document doc = null;
        Document doc1 = null;

        try {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "/Backends1/rest-api/notice/search?keywords=" + this.getParameter("keyword"));
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator("notice");
            while (iter.hasNext()) {
                Element e = (Element) iter.next();
                Moment moment = new Moment();
                String original = e.elementTextTrim("publishTime");
                String year = (original.split("T"))[0];
                String time_o = (original.split("T"))[1];
                String time = (time_o.split("\\+"))[0];
                String date = year + "  " + time;
                moment.setDate(date);
                moment.setMomentID(e.elementTextTrim("NId"));
                moment.setContent(e.elementTextTrim("text"));
                Element e1 = e.element("publisher");
                moment.setName(e1.elementTextTrim("name"));
                //二次请求获得头像
                Client client1 = ClientBuilder.newClient();
                WebTarget target1 = client1.target(URL + "/Backends1/rest-api/filesse/student/" + e1.elementTextTrim("SId"));
                String response1 = target1.request().get(String.class);
                doc1 = DocumentHelper.parseText(response1);
                Element root1 = doc1.getRootElement();
                Iterator iter1 = root1.elementIterator("filesse");
                Element e2 = null;
                while (iter1.hasNext()) {

                    e2 = (Element) iter1.next();
                }

                if (e2 == null) {

                    moment.setImg("./resources/image/dev1ce.jpg");
                } else {
                    moment.setImg(URL+e2.elementTextTrim("url"));
                }
                result.add(moment);
            }
        } catch (Exception e) {

        }

        return result;
    }
//     创建班级相册

    public String createCA() {
        BufferedReader br = null;
        try {
            URL search = new URL(URL + "/Backends1/rest-api/album");
            HttpURLConnection conn = (HttpURLConnection) search.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            OutputStreamWriter ps = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            //{"title":"name","intro":"","inKlass":"classID","grade":""}
            String json = "{\"albTitle\":\"" + this.alb.getAlbumname() + "\",\"albIntro\":\"" + "\"," + "\"inKlass\":\""+ (this.getKG())[0]+ "\",\"grade\":\"\"}";
            ps.write(json);
            ps.close();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            if ((line = br.readLine()) != null) {
                return "/album.xhtml?faces-redirect=true";
            } else {
                return "/album.xhtml?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "/album.xhtml?faces-redirect=true";
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {

            }

        }
    }

    public String createGA() {
        BufferedReader br = null;
        try {
            URL search = new URL(URL + "/Backends1/rest-api/album");
            HttpURLConnection conn = (HttpURLConnection) search.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            OutputStreamWriter ps = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            //{"title":"name","intro":"","inKlass":"classID","grade":""}
            String a = (this.getKG())[1];
            String json = "{\"albTitle\":\"" + this.alb.getAlbumname() + "\",\"albIntro\":\"" + "\",\"inKlass\":\"\",\"grade\":\"" + (this.getKG())[1] + "\"}";
            ps.write(json);
            ps.close();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            if ((line = br.readLine()) != null) {
                return "/album.xhtml?faces-redirect=true";
            } else {
                return "/album.xhtml?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "/album.xhtml?faces-redirect=true";
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {

            }

        }
    }

    public String createAA() {
        BufferedReader br = null;
        try {
            URL search = new URL(URL + "/Backends1/rest-api/album");
            HttpURLConnection conn = (HttpURLConnection) search.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            OutputStreamWriter ps = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            //{"title":"name","intro":"","inKlass":"classID","grade":""}
            String json = "{\"albTitle\":\"" + this.alb.getAlbumname() + "\",\"albIntro\":\"" + "\",\"inKlass\":\"\",\"grade\":\"\"}";
            ps.write(json);
            ps.close();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            if ((line = br.readLine()) != null) {
                return "/album.xhtml?faces-redirect=true";
            } else {
                return "/album.xhtml?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "/album.xhtml?faces-redirect=true";
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {

            }

        }
    }
}
