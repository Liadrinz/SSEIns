/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.service;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Album;
import com.monkey.backends1.Filesse;
import com.monkey.backends1.Klass;
import com.monkey.backends1.Student;
import com.monkey.backends1.serializer.AlbumSerializer;
import com.monkey.backends1.util.Tokener;
import java.util.ArrayList;
import java.util.Collection;
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
@Path("album")
public class AlbumFacadeREST extends AbstractFacade<Album> {

    @PersistenceContext(unitName = "com.monkey_Backends1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AlbumFacadeREST() {
        super(Album.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Album create(@QueryParam("token") String token, JSONObject data) {
        Album entity = AlbumSerializer.unserialize(data, em);
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@QueryParam("token") String token, @PathParam("id") Integer id, JSONObject data) {
        Album entity = AlbumSerializer.unserialize(data, em);
        entity.setAlbId(id);
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
    public Album find(@QueryParam("token") String token, @PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("klass/{kId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Album> findKlassAlbum(@QueryParam("token") String token, @PathParam("kId") Integer kId) {
        Query qalbum = em.createNamedQuery("Album.findKlassAlbum"), qklass = em.createNamedQuery("Klass.findByKId");
        qklass.setParameter("kId", kId);
        qalbum.setParameter("klass", (Klass)qklass.getSingleResult());
        return qalbum.getResultList();
    }
    
    @GET
    @Path("grade/{grade}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Album> findGradeAlbum(@QueryParam("token") String token, @PathParam("grade") String grade) {
        Query qalbum = em.createNamedQuery("Album.findGradeAlbum");
        qalbum.setParameter("grade", grade);
        return qalbum.getResultList();
    }
    
    @GET
    @Path("academy")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Album> findAcademyAlbum(@QueryParam("token") String token) {
        Query qalbum = em.createNamedQuery("Album.findAcademyAlbum");
        return qalbum.getResultList();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Album> findAll(@QueryParam("token") String token) {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Album> findRange(@QueryParam("token") String token, @PathParam("from") Integer from, @PathParam("to") Integer to) {
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
