/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.serializer;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Notice;
import com.monkey.backends1.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 李阿俊
 */
public class NoticeSerializer {
    public static Notice unserialize(JSONObject data, EntityManager em) {
        Notice notice = new Notice();
        notice.setText(data.getString("text"));
        
        Query qstudent = em.createNamedQuery("Student.findBySId");
        qstudent.setParameter("sId", data.getInteger("publisher"));
        List<Student> publishers = qstudent.getResultList();
        if (publishers.size() > 0) {
            notice.setPublisher(publishers.get(0));
        }
        
        return notice;
    }
}
