/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.tyf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;  
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;
import java.util.stream.Collectors;
  
import org.dom4j.Attribute;  
import org.dom4j.Document;  
import org.dom4j.DocumentException;  
import org.dom4j.DocumentHelper;
import org.dom4j.Element;  
import org.dom4j.io.SAXReader; 
  
/**
 *
 * @author tyf
 */
@Named(value = "detail")
@Dependent

public class Detail {

    @Resource(name = "sse")
    private DataSource sse;
    /**
     * Creates a new instance of Detail
     */
    private String sid="";
    private String name="";
    private String sex="";
    private String nation="";
    private Date birthday=new Date(1519-1900, 9-1, 5);
    private SimpleDateFormat birthday2 = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
    private String birthday3;
    private String status="";
    private String banji="";
    private String emotion="";
    private String company="";
    private String location="";
    private String url="";
    private String klass="";
    private int kid;
    private long male;
    private long female;
    private long others;
    private int stunum;
    private double p1;
    private double p2;
    private double p3;
    private Document doc=null;
    private Document doc2=null;
    private Document doc3=null;
    private List<Detail> detaillist;
    private Detail detail = null; 
    private Detail details = null;  
     private List<Detail> detaillist1 =new ArrayList<Detail>();
      private List<Detail> detaillist2 =new ArrayList<Detail>();
      private List<Detail> detaillistss =new ArrayList<Detail>();
     private List<Detail> detaillists =new ArrayList<Detail>();
     private Client client2;
     private Client client3;
     private WebTarget target2;
      private WebTarget target3;
     
    public Detail() throws DocumentException {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://10.128.216.20:8080/Backends1/rest-api/student");
            String response = target.request().get(String.class);
          client2 = ClientBuilder.newClient();
          client3 =  ClientBuilder.newClient();
          
            doc=DocumentHelper.parseText(response);

    
     
    }
      public List<Detail> gotDetails() throws DocumentException{  
          
        SAXReader reader = new SAXReader();  
        try {  
            Element detailstore = doc.getRootElement();  
            Iterator storeit = detailstore.elementIterator();  
              
            detaillist = new ArrayList<Detail>();  
            while(storeit.hasNext()){  
                  
                detail = new Detail();  
                Element detailElement = (Element) storeit.next();  
                //遍历detailElement的属性  
                List<Attribute> attributes = detailElement.attributes();  
//                for(Attribute attribute : attributes){  
//                    if(attribute.getName().equals("SId")){  
//                  
//      
//                    }  
//                }  
                  
                Iterator detailit = detailElement.elementIterator();  
                while(detailit.hasNext()){  
                    Element child = (Element) detailit.next();  
                    String nodeName = child.getName();  
                    if(nodeName.equals("name")){   
                        String name = child.getStringValue();  
                        detail.setName(name);  
                    }else if(nodeName.equals("sex")){  
                        String sex = child.getStringValue();  
                        detail.setSex(sex);
                    }else if(nodeName.equals("SId")){  
                        String sid1 = child.getStringValue();  
                        detail.setSid(sid1);
               
                    }else if(nodeName.equals("ethnic")){  
                        String ethnic = child.getStringValue();  
                        detail.setNation(ethnic);
                    }else if(nodeName.equals("birthday")){  
                        String birthdayy = child.getStringValue();
                       detail.setBirthday3(birthdayy);
                    }
                    else if(nodeName.equals("status")){  
                        String status = child.getStringValue();
                        detail.setStatus(status);
                    }  
                    else if(nodeName.equals("klass")){  
                          Iterator detailitt = child.elementIterator();  
                          while(detailit.hasNext()){ 
                              Element childd = (Element) detailitt.next();
                                String nodeNamee = childd.getName();
                              if(nodeNamee.equals("num")){  
                                  String num = childd.getStringValue();
                                  detail.setBanji(num);
                                  break;
                              }
                              if(nodeNamee.equals("KId")){  
                                  String kid = childd.getStringValue();
                                  detail.setKid(Integer.parseInt(kid));
                                  
                              }
                          }
                    }  
                    else if(nodeName.equals("relationship")){  
                        String relationship = child.getStringValue();
                        detail.setEmotion(relationship);
                    }  
                    else if(nodeName.equals("company")){  
                        String company  = child.getStringValue();
                        detail.setCompany(company);
                    }
                    else if(nodeName.equals("location")){  
                        String location = child.getStringValue();
                        detail.setLocation(location);
                    }  
                }  
                detaillist.add(detail);  
                detail = null;  
                  
            }  
        } catch (DocumentException e) {  
          
            e.printStackTrace();  
        }
           
        int x=(int)(1+Math.random()*(4-1+1)); 
       
       Map<String, Long> map = detaillist.stream().collect(Collectors.groupingBy(Detail::getSex,Collectors.counting()));
       
      male=map.get("男");
      female=map.get("女");
      if(male==0)
      {
          p1=0;
          p2=100;
          p1=0;
      }
      else if(female==0)
      {
          p1=100;
          p2=0;
          p3=0;
      }
      else
      {
          p1=(double)male/(male+female);
          p2=(double)female/(male+female);
          p1=p1*100;
          p2=p2*100;
          p3=0;
      }
      this.sid=detaillist.get(x-1).sid; 
       this.name=detaillist.get(x-1).name; 
        this.sex=detaillist.get(x-1).sex;
        this.nation=detaillist.get(x-1).nation; 
        this.birthday3=detaillist.get(x-1).birthday3;
        if(detaillist.get(x-1).birthday3!=null)
           detaillist.get(x-1).birthday3=detaillist.get(x-1).birthday3.substring(0, 10);
        this.status=detaillist.get(x-1).status; 
        this.banji=detaillist.get(x-1).banji;
        this.emotion=detaillist.get(x-1).emotion; 
        this.company=detaillist.get(x-1).company;
         detaillist.get(x-1).p1=this.p1; 
       detaillist.get(x-1).p2=this.p2;
      detaillist.get(x-1).p3=this.p3;
            target2 = client2.target("http://10.128.216.20:8080/Backends1/rest-api/filesse/student/"+sid);
            String response2 = target2.request().get(String.class);
            doc2=DocumentHelper.parseText(response2);
             Element detailstores = doc2.getRootElement();  
            Iterator storeits = detailstores.elementIterator();   
            detaillists = new ArrayList<Detail>();  
            while(storeits.hasNext()){                 
                details = new Detail();  
                Element detailElements = (Element) storeits.next();  
                //遍历detailElement的属性       
                Iterator detailits = detailElements.elementIterator();  
                while(detailits.hasNext()){  
                    Element childs = (Element) detailits.next();  
                    String nodeNames = childs.getName();  
                    if(nodeNames.equals("url")){   
                        String url = childs.getStringValue();  
                        details.setUrl(url);
                    }}
                 detaillists.add(details);  
                details = null;  
            }
            if(detaillists.size()>0)
          detaillist.get(x-1).url=detaillists.get(detaillists.size()-1).url;
        detaillist1.add(detaillist.get(x-1));
        System.out.println(p1+"   "+p2);
        return detaillist1;
          
    }
  
    
    //--------分--------------------割--------------------------线-----------------------------
      
          public void setStunum(int stunum) {
        this.stunum = stunum;
    }

    public int getStunum() {
        return stunum;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getKlass() {
        return klass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {    
        this.location = location;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public int getKid() {
        return kid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getP3() {
        return p3;
    }

    public double getP1() {
        return p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public String getBirthday3() {
        return birthday3;
    }

    public void setBirthday3(String birthday3) {
        this.birthday3 = birthday3;
    }
    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getNation() {
        return nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public SimpleDateFormat getBirthday2() {
        return birthday2;
    }

    public String getStatus() {
        return status;
    }

    public String getBanji() {
        return banji;
    }

    public String getEmotion() {
        return emotion;
    }

    public String getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday2(SimpleDateFormat birthday2) {
        this.birthday2 = birthday2;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public void setEmotion(String emption) {
        this.emotion = emption;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
}
