/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.hxl.beans;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monkey.hxl.enit.Comment;
import com.monkey.hxl.enit.Notice;
import com.monkey.hxl.enit.ToolUtils;
import com.monkey.hxl.enit.WriteComment;
import com.monkey.liadrinz.frontend.login.LoginBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author ahuan
 */
@Named("notice")
@ViewScoped
public class NoticBean implements Serializable {
    
    @EJB
    protected LoginBean loginbean;
    protected Integer sId;

    public Integer getsId() {
        this.sId = loginbean.getsId();
        return sId;
    }


    protected String publishername;
    protected String publishtime;
    protected String noticecontent;
    protected String writeText;
    protected String writeNid;
    protected List<Map<String, String>> list;
    private static final Logger logger = Logger.getLogger("firstcup.web.DukesBDay");
   
    
    public String getWriteText() {
        return writeText;
    }
    public String getTitlehead(){
        String headurl = new String();
        String tsid = String.valueOf(loginbean.getsId());
        headurl = NoticBean.getHead(tsid);
        return headurl;
    }
    
    public void setWriteText(String writeText) {
        this.writeText = writeText;
    }

    public String getWriteNid() {
        return writeNid;
    }

    public void setWriteNid(String writeNid) {
        this.writeNid = writeNid;
    }
    public static List getRandomNumList(int nums, int start, int end) {
        //1.创建集合容器对象
        List list = new ArrayList();

        //2.创建Random对象
        Random r = new Random();
        //循环将得到的随机数进行判断，如果随机数不存在于集合中，则将随机数放入集合中，如果存在，则将随机数丢弃不做操作，进行下一次循环，直到集合长度等于nums
        while (list.size() != nums) {
            int num = r.nextInt(end - start) + start;
            if (!list.contains(num)) {
                list.add(num);
            }
        }

        return list;
    }

    public NoticBean() {
    }
    public List<String> getLbimg(){
        Document doc = null;
        List<String> list = new ArrayList<String>();
        try{
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(Notice.url + "/Backends1/rest-api/filesse/");
             String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element rootElt = doc.getRootElement();
            Iterator iter = rootElt.elementIterator("filesse");
           List<String> allimg = new ArrayList<String>(); 
            while(iter.hasNext()){
                Element ex = (Element) iter.next();
                if(ex.elementTextTrim("inNotice") != null){
                    allimg.add(Notice.getUrl()+ex.elementTextTrim("url"));
                }
            }
             List<Integer> rlist = getRandomNumList(3,0,allimg.size());
            for(int rnum = 0;rnum<rlist.size();rnum++){
                list.add(allimg.get(rlist.get(rnum)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<Notice> showNotice() {
        List<Notice> list = new ArrayList<Notice>();
        List<Comment> comlist = new ArrayList<Comment>();
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target1 = client.target(Notice.url + "/Backends1/rest-api/notice/count");
            String ncount = target1.request().get(String.class);
            int maxnnum = Integer.parseInt(ncount)-1;
            int snmun = maxnnum - 3;
            WebTarget target = client.target(Notice.url + "/Backends1/rest-api/notice/"+snmun+"/"+maxnnum);
            
            String response = target.request().get(String.class);
            List<Notice> list1 = NoticBean.XmltoString(response);
            
            list = NoticBean.ChangeList(list1);
            for(int i =0;i<list.size();i++){
                String nnid = list.get(i).getNid();
                List<String> gsit = NoticBean.getNoticeimg(nnid);
                int j =0;
                Notice notice = new Notice();
                notice = list.get(i);
                while(gsit.size()>0){
                    if (j < gsit.size()) {
                        if (j == 0) {
                            notice.setImglist1(Notice.getUrl()+gsit.get(0));
                            j++;
                            continue;
                        } else if (j == 1) {
                            notice.setImglist2(Notice.getUrl()+gsit.get(1));
                            j++;
                            continue;
                        } else if (j == 2) {
                            notice.setImglist3(Notice.getUrl()+gsit.get(2));
                            j++;
                            continue;
                        } else if (j == 3) {
                            notice.setImglist4(Notice.getUrl()+gsit.get(3));
                            j = 0;
                            break;

                        }
                    }else{
                        break;
                    }
               
                    
                } 
                list.set(i,notice);
            }
            
        } catch (Exception e) {
            return new ArrayList<Notice>();
        }

        return list;
    }

    public static List<Notice> ChangeList(List<Notice> list) {
        List<Notice> answer = new ArrayList<Notice>();
        try {
            for (int i = list.size() - 1; i >= 0; i--) {
                answer.add(list.get(i));
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static List<Notice> XmltoString(String xml) {
        List<Notice> list = new ArrayList<Notice>();

        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
            Element rootElt = doc.getRootElement();
            Iterator iter = rootElt.elementIterator("notice");
            while (iter.hasNext()) {
                Notice notice = new Notice();
                Element e = (Element) iter.next();
                notice.setNid(e.elementTextTrim("NId"));               
                String pdate = NoticBean.rewriteDate(e.elementTextTrim("publishTime"));
                notice.setPubdate(pdate);

                Iterator itet = e.elementIterator("publisher");
                while (itet.hasNext()) {
                    Element et = (Element) itet.next();
                    notice.setPubname(et.elementTextTrim("name"));
                    String s = et.elementTextTrim("SId");
                   
                    notice.setHead(NoticBean.getHead(s));

                }
                notice.setPubtext(e.elementTextTrim("text"));

                list.add(notice);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<Comment> getComment(String nid) {
        List<Comment> allcomlist = NoticBean.getAllcomment();
        List<Comment> an = new ArrayList<Comment>();
        try {
            for (int i = 0; i <= allcomlist.size(); i++) {
                if (allcomlist.get(i).getNid().equals(nid)) {
                    an.add(allcomlist.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return an;
    }

    public static List<Comment> getAllcomment() {
        List<Comment> allcomList = new ArrayList<Comment>();
        Document doc = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(Notice.url + "/Backends1/rest-api/comment");
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element rootElt = doc.getRootElement();
            Iterator iter = rootElt.elementIterator("comment");
            while (iter.hasNext()) {
                Comment comment = new Comment();
                Element e = (Element) iter.next();
                String cdate = NoticBean.rewriteDate(e.elementTextTrim("commentTime"));
                comment.setComdate(cdate);
                comment.setComtext(e.elementTextTrim("content"));
                Iterator itet = e.elementIterator("commenter");
                while (itet.hasNext()) {
                    Element et = (Element) itet.next();
                    comment.setComname(et.elementTextTrim("name"));
                    String s = et.elementTextTrim("SId");
                    comment.setHead(NoticBean.getHead(s));
                    
                }
                Iterator itee = e.elementIterator("notice");
                while (itee.hasNext()) {
                    Element ex = (Element) itee.next();
                    comment.setNid(ex.elementTextTrim("NId"));
                }
                allcomList.add(comment);
            }

        } catch (Exception e) {
            return new ArrayList<Comment>();
        }

        return allcomList;
    }

    public static String rewriteDate(String date) {
        String bdate = new String();
        try {
            bdate = date.substring(0, 10) + " " + date.substring(11, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bdate;
    }

    public String getPublishername() {
        return publishername;
    }

    public String WriteComment() {
        String url = Notice.url + "/Backends1/rest-api/comment";
        try {
            URL commentUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) commentUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            OutputStreamWriter ps = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            WriteComment writecomment = new WriteComment();
            writecomment.setNotice("1");
            writecomment.setCommenter("1");
           
            writecomment.setContent(this.writeText);
            String json = JSON.toJSONString(writecomment, true);
            ps.write(json);
            ps.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                return "success";
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no";
    }

    public static String getHead(String id) {
        String imgUrl = new String();
        Document doc = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(Notice.url + "/Backends1/rest-api/filesse/student/" + id);
            String response = target.request().get(String.class);
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator("filesse");

            while (iter.hasNext()) {
                Element ex = (Element) iter.next();
                String url = ex.elementTextTrim("url");
                
                if (!url.isEmpty()) {
                    imgUrl = Notice.url + url;
                } else {
                    imgUrl = "../Frontend/resources/images/szc.png";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
    }
    public static List<String> getNoticeimg(String nid){
        List<String> imglist = new ArrayList<String>();
        Document doc = null;
        try{
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(Notice.url + "/Backends1/rest-api/filesse/notice/" + nid);
            String response = target.request().get(String.class);
           
            doc = DocumentHelper.parseText(response);
            Element e = doc.getRootElement();
            Iterator itet = e.elementIterator("filesse");
            while(itet.hasNext()){
                Element x = (Element) itet.next();
                imglist.add(x.elementTextTrim("url"));
                logger.log(Level.INFO, "dwdwd：{0}", x.elementTextTrim("url"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return imglist;

    }
    
    public void setPublishername(String publishername) {
        this.publishername = publishername;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getNoticecontent() {
        return noticecontent;
    }

    public void setNoticecontent(String noticecontent) {
        this.noticecontent = noticecontent;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }

}
