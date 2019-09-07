/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.szc.response;

import com.monkey.liadrinz.frontend.login.LoginBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.monkey.szc.classes.Student;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author hp
 */
@Named
@RequestScoped
public class GetStudent {
    @EJB
    protected LoginBean loginbean;
    protected Integer sid ;

    public Integer getSid() {
        this.sid =  loginbean.getsId();
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
   
    public static String getParameter(String para) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getParameter(para);
    }
   

    void GetStudent() {

    }
    
    public String rewriteDate(String date)
    {
        if(date == null)
            return "null";
        String substr = date.substring(0,10);
        return substr;
    }
    
    public List<Student> getotherMessage() {
        List<Student> messages = new ArrayList<Student>();
         Document doc = null;
        
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://10.128.216.20:8080/Backends1/rest-api/student");
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element rootElt = doc.getRootElement();
            Iterator iter = rootElt.elementIterator("student");
            while (iter.hasNext()) {
                Student student = new Student();
                Element e = (Element)iter.next();
                String bd = this.rewriteDate(e.elementTextTrim("birthday"));
                student.setBirthday(bd);
                student.setBuptId(e.elementTextTrim("buptId"));
                student.setCompany(e.elementTextTrim("company"));
                student.setEmail(e.elementTextTrim("email"));
                student.setEthnic(e.elementTextTrim("ethnic"));
                student.setIntro(e.elementTextTrim("intro"));
                
                Iterator iter2 = e.elementIterator("klass");
                while(iter2.hasNext())
                {
                    Element e2 = (Element)iter2.next();
                    student.setKid(e2.elementTextTrim("num"));
                    
                }
                
                student.setLocation(e.elementTextTrim("location"));
                student.setName(e.elementTextTrim("name"));
                student.setRelationship(e.elementTextTrim("relationship"));
                int i = Integer.parseInt(e.elementTextTrim("SId"));
                student.setSId(i);
                student.setSex(e.elementTextTrim("sex"));
                student.setStatus(e.elementTextTrim("status"));
                student.setTel(e.elementTextTrim("tel"));
                
                
                messages.add(student);
                
                
            }

        } catch (DocumentException ex) {
            Logger.getLogger(GetStudent.class.getName()).log(Level.SEVERE, null, ex);
        }


   
        return getUser(messages);
    }
    
    public List<Student> getMyMessage() {
         List<Student> messages = new ArrayList<Student>();
         Document doc = null;
        
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://10.128.216.20:8080/Backends1/rest-api/student");
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element rootElt = doc.getRootElement();
            Iterator iter = rootElt.elementIterator("student");
            while (iter.hasNext()) {
                Student student = new Student();
                Element e = (Element)iter.next();
                String bd = this.rewriteDate(e.elementTextTrim("birthday"));
                student.setBirthday(bd);
                student.setBuptId(e.elementTextTrim("buptId"));
                student.setCompany(e.elementTextTrim("company"));
                student.setEmail(e.elementTextTrim("email"));
                student.setEthnic(e.elementTextTrim("ethnic"));
                student.setIntro(e.elementTextTrim("intro"));
                
                Iterator iter2 = e.elementIterator("klass");
                while(iter2.hasNext())
                {
                    Element e2 = (Element)iter2.next();
                    student.setKid(e2.elementTextTrim("num"));
                    
                }
                
                student.setLocation(e.elementTextTrim("location"));
                student.setName(e.elementTextTrim("name"));
                student.setRelationship(e.elementTextTrim("relationship"));
                int i = Integer.parseInt(e.elementTextTrim("SId"));
                student.setSId(i);
                student.setSex(e.elementTextTrim("sex"));
                student.setStatus(e.elementTextTrim("status"));
                student.setTel(e.elementTextTrim("tel"));
                
                
                
                messages.add(student);
                
            }

        } catch (DocumentException ex) {
            Logger.getLogger(GetStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        List<Student> user = new ArrayList<Student>();
        
        int i = 0;
        while(messages.get(i).getSId() != this.getSid())
            i++;
         
         user.add(messages.get(i));
        
        
        return user;
    }
     
    public List<Student> getUser(List<Student> list)
    {
        List<Student> user = new ArrayList<Student>();
        
        int i = 0;
        
       
        int id = Integer.parseInt(GetStudent.getParameter("sid"));
        
        while(list.get(i).getSId() != id)
            i++;
        
        user.add(list.get(i));
        
        return user;
        
    }
    
}
