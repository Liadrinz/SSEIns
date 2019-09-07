/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.service;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Album;
import com.monkey.backends1.Filesse;
import com.monkey.backends1.Notice;
import com.monkey.backends1.Student;
import com.monkey.backends1.serializer.FilesseSerializer;
import com.monkey.backends1.util.BaseToFile;
import com.monkey.backends1.util.Encrypt;
import com.monkey.backends1.util.Tokener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author ahuan
 */
@Stateless
@Path("filesse")
public class FilesseFacadeREST extends AbstractFacade<Filesse> {

    private final String filePath = "D:\\github\\uploadFile\\";
    private final String rootUrl = "/Backends1/rest-api/filesse/images/";

    @PersistenceContext(unitName = "com.monkey_Backends1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public FilesseFacadeREST() {
        super(Filesse.class);
    }

    @GET
    @Produces("image/png")
    @Path("images/{fname}")
    public Response download(@PathParam("fname") String fname) {
        File file = new File(filePath + "/" + fname);
        ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    }

    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("upload")
    public Filesse upload(JSONObject data) throws Exception {
        String base64Code = data.getString("base64");
        String suffix = data.getString("type");

        String newFileName = Encrypt.hash(base64Code);
        BaseToFile.decoderBase64File(base64Code, filePath + newFileName, filePath, suffix);

        String imgUrl = rootUrl + newFileName + "." + suffix;
        Filesse image;
        image = new Filesse();
        image.setUrl(imgUrl);
        em.persist(image);
        return image;
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
    public Filesse find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("student/{sId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Filesse> findStudentAvatar(@PathParam("sId") Integer sId) {
        Query qfile = em.createNamedQuery("Filesse.findAvatarBySId"), qstudent = em.createNamedQuery("Student.findBySId");
        qstudent.setParameter("sId", sId);
        qfile.setParameter("sId", (Student)qstudent.getSingleResult());
        List<Filesse> files = qfile.getResultList();
        return files;
    }

    @GET
    @Path("notice/{nId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Filesse> findNoticePhotos(@PathParam("nId") Integer nId) {
        Query qfile = em.createNamedQuery("Filesse.findPhotoByNId"), qnotice = em.createNamedQuery("Notice.findByNId");
        qnotice.setParameter("nId", nId);
        qfile.setParameter("nId", (Notice)qnotice.getSingleResult());
        List<Filesse> files = qfile.getResultList();
        return files;
    }

    @GET
    @Path("album/{albId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Filesse> findAlbumPhotos(@PathParam("albId") Integer albId) {
        Query qfile = em.createNamedQuery("Filesse.findPhotoByAlbId"), qalbum = em.createNamedQuery("Album.findByAlbId");
        qalbum.setParameter("albId", albId);
        qfile.setParameter("albId", (Album)qalbum.getSingleResult());
        List<Filesse> files = qfile.getResultList();
        return files;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Filesse> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Filesse> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Integer id, JSONObject data) {
        Query qfile = em.createNamedQuery("Filesse.findByFId");
        qfile.setParameter("fId", id);
        List<Filesse> files = qfile.getResultList();
        if (files.size() > 0) {
            Filesse file = files.get(0);
            if (!data.getString("inAlbum").equals("")) {
                Query qalbum = em.createNamedQuery("Album.findByAlbId");
                qalbum.setParameter("albId", data.getInteger("inAlbum"));
                List<Album> albums = qalbum.getResultList();
                if (albums.size() > 0) {
                    file.setInAlbum(albums.get(0));
                }
            } else {
                file.setInAlbum(null);
            }

            if (!data.getString("inNotice").equals("")) {
                Query qnotice = em.createNamedQuery("Notice.findByNId");
                qnotice.setParameter("nId", data.getInteger("inNotice"));
                List<Notice> notices = qnotice.getResultList();
                if (notices.size() > 0) {
                    file.setInNotice(notices.get(0));
                }
            } else {
                file.setInNotice(null);
            }

            if (!data.getString("inStudent").equals("")) {
                Query qstudent = em.createNamedQuery("Student.findBySId");
                qstudent.setParameter("sId", data.getInteger("inStudent"));
                List<Student> students = qstudent.getResultList();
                if (students.size() > 0) {
                    file.setInStudent(students.get(0));
                }
            } else {
                file.setInStudent(null);
            }
            super.edit(file);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
