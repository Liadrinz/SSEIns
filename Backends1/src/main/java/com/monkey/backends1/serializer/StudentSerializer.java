/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.serializer;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Filesse;
import com.monkey.backends1.Klass;
import com.monkey.backends1.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 李阿俊
 */
@Stateless
public class StudentSerializer {

    public static Student unserialize(JSONObject data, EntityManager em, Integer id) throws Exception {
        Student student;
        if (id == null) {
            student = new Student();
            student.setBuptId(data.getString("buptId"));
            student.setName(data.getString("name"));
            student.setTel(data.getString("tel"));
            student.setEmail(data.getString("email"));
            student.setSex(data.getString("sex"));
            student.setStatus(data.getString("status"));
            student.setPassword(data.getString("password"));
            student.setBirthday(data.getDate("birthday"));
            student.setEthnic(data.getString("ethnic"));
            student.setCompany(data.getString("company"));
            student.setRelationship(data.getString("relationship"));
            student.setLocation(data.getString("location"));
            student.setIntro(data.getString("intro"));

            if (!data.getString("klass").equals("")) {
                Query qklass = em.createNamedQuery("Klass.findByKId");
                qklass.setParameter("kId", data.getInteger("klass"));
                student.setKlass((Klass) qklass.getSingleResult());
            }

            return student;

        } else {
            Query qstudent = em.createNamedQuery("Student.findBySId");
            qstudent.setParameter("sId", id);
            List<Student> students = qstudent.getResultList();
            if (students.size() > 0) {
                student = students.get(0);
                student.setName(data.getString("name"));
                student.setTel(data.getString("tel"));
                student.setEmail(data.getString("email"));
                student.setSex(data.getString("sex"));
                student.setStatus(data.getString("status"));
                student.setBirthday(data.getDate("birthday"));
                student.setEthnic(data.getString("ethnic"));
                student.setCompany(data.getString("company"));
                student.setRelationship(data.getString("relationship"));
                student.setLocation(data.getString("location"));
                student.setIntro(data.getString("intro"));

                return student;
            }
            return null;
        }
    }
}
