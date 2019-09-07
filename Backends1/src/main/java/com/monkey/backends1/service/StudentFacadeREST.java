/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.service;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Klass;
import com.monkey.backends1.Student;
import com.monkey.backends1.Token;
import com.monkey.backends1.serializer.StudentSerializer;
import com.monkey.backends1.util.Encrypt;
import com.monkey.backends1.util.SQLGenerator;
import com.monkey.backends1.util.Tokener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ahuan
 */
@Stateless
@Path("student")
public class StudentFacadeREST extends AbstractFacade<Student> {

    @PersistenceContext(unitName = "com.monkey_Backends1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public StudentFacadeREST() {
        super(Student.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student create(JSONObject data) throws Exception {
        Student entity = StudentSerializer.unserialize(data, em, null);
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@QueryParam("token") String token, @PathParam("id") Integer id, JSONObject data) throws Exception {
        int result = Tokener.valid(token, Tokener.aDay, em);
        if (result == 0 || result == id) {
            Student entity = StudentSerializer.unserialize(data, em, id);
            entity.setSId(id);
            super.edit(entity);
        } else {
            throw new Exception("Forbidden");
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@QueryParam("token") String token, @PathParam("id") Integer id) throws Exception {
        if (Tokener.valid(token, Tokener.aDay, em) != 0 || id == 0) {
            throw new Exception("Forbidden");
        }
        super.remove(super.find(id));
    }

    @GET
    @Path("expired")
    public Integer expired(@QueryParam("token") String token) {
        return Tokener.valid(token, Tokener.aDay, em);
    }

    @GET
    @Path("ratio/total")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Map<String, Map> getTotalRatio() {
        Integer male, female;
        Map<String, Integer> provinces = new HashMap<>();
        Query qsex = em.createNamedQuery("Student.findBySex");
        qsex.setParameter("sex", "男");
        male = qsex.getResultList().size();
        qsex.setParameter("sex", "女");
        female = qsex.getResultList().size();
        Map<String, Integer> sexRatio = new HashMap<>();
        sexRatio.put("male", male);
        sexRatio.put("female", female);
        for (Student student : (List<Student>) em.createNamedQuery("Student.findAll").getResultList()) {
            if (student.getLocation() != null && !student.getLocation().equals("")) {
                if (provinces.containsKey(student.getLocation())) {
                    provinces.put(student.getLocation(), provinces.get(student.getLocation()) + 1);
                } else {
                    provinces.put(student.getLocation(), 1);
                }
            }
        }
        Map<String, Map> result = new HashMap<>();
        result.put("sexRatio", sexRatio);
        result.put("provinces", provinces);
        return result;
    }

    @GET
    @Path("ratio/klass/{kId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Map<String, Map> getKlassRatio(@PathParam("kId") Integer kId) {
        List<Student> students = this.findByKlass(kId);
        Integer male = 0, female = 0;
        Map<String, Integer> provinces = new HashMap<>();
        for (Student student : students) {
            if (student.getSex() == "男") {
                ++male;
            } else {
                ++female;
            }
            if (student.getLocation() != null && !student.getLocation().equals("")) {
                if (provinces.containsKey(student.getLocation())) {
                    provinces.put(student.getLocation(), provinces.get(student.getLocation()) + 1);
                } else {
                    provinces.put(student.getLocation(), 1);
                }
            }
        }
        Map<String, Integer> sexRatio = new HashMap<>();
        sexRatio.put("male", male);
        sexRatio.put("female", female);
        Map<String, Map> result = new HashMap<>();
        result.put("sexRatio", sexRatio);
        result.put("provinces", provinces);
        return result;
    }

    @GET
    @Path("ratio/grade/{grade}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Map<String, Map> getKlassRatio(@PathParam("grade") String grade) {
        List<Student> students = this.findByGrade(grade);
        Integer male = 0, female = 0;
        Map<String, Integer> provinces = new HashMap<>();
        for (Student student : students) {
            if (student.getSex() == "男") {
                ++male;
            } else {
                ++female;
            }
            if (student.getLocation() != null && !student.getLocation().equals("")) {
                if (provinces.containsKey(student.getLocation())) {
                    provinces.put(student.getLocation(), provinces.get(student.getLocation()) + 1);
                } else {
                    provinces.put(student.getLocation(), 1);
                }
            }
        }
        Map<String, Integer> sexRatio = new HashMap<>();
        sexRatio.put("male", male);
        sexRatio.put("female", female);
        Map<String, Map> result = new HashMap<>();
        result.put("sexRatio", sexRatio);
        result.put("provinces", provinces);
        return result;
    }

    @GET
    @Path("klass/{kId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findByKlass(@PathParam("kId") Integer kId) {
        Query qklass = em.createNamedQuery("Klass.findByKId");
        qklass.setParameter("kId", kId);
        Klass klass = (Klass) qklass.getSingleResult();
        Query qstudent = em.createNamedQuery("Student.findByKlass");
        qstudent.setParameter("klass", klass);
        return qstudent.getResultList();
    }

    @GET
    @Path("grade/{grade}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findByGrade(@PathParam("grade") String grade) {
        Query qstudent = em.createNamedQuery("Student.findByGrade");
        qstudent.setParameter("grade", grade);
        return qstudent.getResultList();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("search")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findByKeywords(@QueryParam("keywords") String keywords) {
        String[] fileds = {"s.name", "s.buptId", "s.email", "s.status", "s.relationship", "s.location", "s.tel", "s.company", "s.intro"};
        String sql = SQLGenerator.generateSQL("SELECT s FROM Student s WHERE ", fileds, keywords);
        Query qstudent = em.createQuery(sql);
        return qstudent.getResultList();
    }
    
    @GET
    @Path("klassNum/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> findSIdByKlassNum(@PathParam("num") String num) {
        Query qstudent = em.createNamedQuery("Student.findByKlassNum");
        qstudent.setParameter("num", num);
        List<Integer> result = new ArrayList<>();
        for (Student student : (List<Student>)qstudent.getResultList()) {
            result.add(student.getSId());
        }
        return result;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> login(JSONObject data) throws Exception {
        Map<String, String> result = new HashMap<>();
        String buptId = data.getString("buptId");
        String password = data.getString("password");
        Query qstudent = em.createNamedQuery("Student.findByBuptId");
        qstudent.setParameter("buptId", buptId);
        List<Student> students = qstudent.getResultList();
        if ((students.size() > 0 && students.get(0).checkPassword(password))) {
            Student student = students.get(0);
            String tk = Tokener.generate(student, em);
            result.put("token", tk);
            result.put("id", student.getSId().toString());
            return result;
        } else if (buptId.equals("admin") && password.equals("admin123456")) {
            Query qs = em.createNamedQuery("Student.findBySId");
            qs.setParameter("sId", 0);
            Student student = (Student) qs.getSingleResult();
            String tk = Tokener.generate(student, em);
            result.put("token", tk);
            result.put("id", student.getSId().toString());
            return result;
        } else {
            return null;
        }
    }

    @POST
    @Path("admin/reset")
    @Produces(MediaType.APPLICATION_XML)
    public Student reset(@QueryParam("token") String token, JSONObject data) throws Exception {
        String buptId = data.getString("buptId");
        String password = data.getString("password");
        if (Tokener.valid(token, (1000 * 3600 * 24 * 365L), em) == 0) {
            Query qstudent = em.createNamedQuery("Student.findByBuptId");
            qstudent.setParameter("buptId", buptId);
            List<Student> students = qstudent.getResultList();
            if (students.size() > 0) {
                Student student = students.get(0);
                student.setPassword(password);
                return student;
            }
            return null;
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
