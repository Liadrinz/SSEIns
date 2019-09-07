/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.serializer;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Album;
import com.monkey.backends1.Filesse;
import com.monkey.backends1.Notice;
import com.monkey.backends1.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 李阿俊
 */
public class FilesseSerializer {

    public static Filesse unserialize(JSONObject data, EntityManager em) {
        Filesse file = new Filesse();

        if (!data.getString("inAlbum").equals("")) {
            Query qalbum = em.createNamedQuery("Album.findByAlbId");
            qalbum.setParameter("albId", data.getInteger("inAlbum"));
            List<Album> albums = qalbum.getResultList();
            if (albums.size() > 0) {
                file.setInAlbum(albums.get(0));
            }
        }
        
        if (!data.getString("inNotice").equals("")) {
            Query qnotice = em.createNamedQuery("Notice.findByNId");
            qnotice.setParameter("nId", data.getInteger("inNotice"));
            List<Notice> notices = qnotice.getResultList();
            if (notices.size() > 0) {
                file.setInNotice(notices.get(0));
            }
        }
        
        if (!data.getString("inStudent").equals("")) {
            Query qstudent = em.createNamedQuery("Student.findBySId");
            qstudent.setParameter("sId", data.getInteger("inStudent"));
            List<Student> students = qstudent.getResultList();
            if (students.size() > 0) {
                file.setInStudent(students.get(0));
            }
        }

        return file;
    }
}
