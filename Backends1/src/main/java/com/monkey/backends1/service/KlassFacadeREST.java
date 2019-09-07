/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.service;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Klass;
import com.monkey.backends1.serializer.KlassSerializer;
import com.monkey.backends1.util.Tokener;
import java.util.List;
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
@Path("klass")
public class KlassFacadeREST extends AbstractFacade<Klass> {

    @PersistenceContext(unitName = "com.monkey_Backends1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public KlassFacadeREST() {
        super(Klass.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Klass create(JSONObject data) {
        Klass entity = KlassSerializer.unserialize(data, em);
        Query qklass1 = em.createNamedQuery("Klass.findByGrade"), qklass2 = em.createNamedQuery("Klass.findByNum");
        qklass1.setParameter("grade", entity.getGrade());
        qklass2.setParameter("num", entity.getNum());
        if (qklass1.getResultList().isEmpty() || qklass2.getResultList().isEmpty()) {
            super.create(entity);
            return entity;
        }
        return null;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, JSONObject data) {
        Klass entity = KlassSerializer.unserialize(data, em);
        entity.setKId(id);
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
    public Klass find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Klass> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Klass> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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
