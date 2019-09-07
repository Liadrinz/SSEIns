/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.serializer;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Comment;
import com.monkey.backends1.Notice;
import com.monkey.backends1.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 李阿俊
 */
public class CommentSerializer {
    public static Comment unserialize(JSONObject data, EntityManager em) {
        Comment comment = new Comment();
        comment.setContent(data.getString("content"));
        
        Query qcommenter = em.createNamedQuery("Student.findBySId");
        qcommenter.setParameter("sId", data.getInteger("commenter"));
        List<Student> commenters = qcommenter.getResultList();
        if (commenters.size() > 0) {
            comment.setCommenter(commenters.get(0));
        }
        
        Query qnotice = em.createNamedQuery("Notice.findByNId");
        qnotice.setParameter("nId", data.getInteger("notice"));
        List<Notice> notices = qnotice.getResultList();
        if (notices.size() > 0) {
            comment.setNotice(notices.get(0));
        }
        
        return comment;
    }
}
