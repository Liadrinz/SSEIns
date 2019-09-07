/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.service;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Comment;
import com.monkey.backends1.Notice;
import com.monkey.backends1.Student;
import com.monkey.backends1.serializer.NoticeSerializer;
import com.monkey.backends1.util.SQLGenerator;
import com.monkey.backends1.util.Tokener;
import java.util.List;
import java.util.Objects;
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
@Path("notice")
public class NoticeFacadeREST extends AbstractFacade<Notice> {

    @PersistenceContext(unitName = "com.monkey_Backends1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public NoticeFacadeREST() {
        super(Notice.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Notice create(JSONObject data) {
        Notice entity = NoticeSerializer.unserialize(data, em);
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, JSONObject data) {
        Notice entity = NoticeSerializer.unserialize(data, em);
        entity.setNId(id);
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@QueryParam("token") String token, @PathParam("id") Integer id) throws Exception {
        if (Tokener.valid(token, 1000 * 3600 * 24L, em) != 0) {
            throw new Exception("Forbidden");
        }
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Notice find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notice> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notice> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("search")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notice> findByKeywords(@QueryParam("keywords") String keywords) throws Exception {
        keywords = new String(keywords.getBytes("windows-1252"), "GBK");
        String[] fileds = {"n.publisher.name", "n.text", "n.publisher.name", "n.publisher.buptId", "n.publisher.email", "n.publisher.status", "n.publisher.relationship", "n.publisher.location", "n.publisher.tel", "n.publisher.company", "n.publisher.intro"};
        String sql = SQLGenerator.generateSQL("SELECT n FROM Notice n WHERE ", fileds, keywords);
        Query qnotice = em.createQuery(sql);
        return qnotice.getResultList();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
