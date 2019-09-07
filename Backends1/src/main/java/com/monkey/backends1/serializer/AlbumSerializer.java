/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.serializer;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Album;
import com.monkey.backends1.Klass;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 李阿俊
 */
public class AlbumSerializer {
    public static Album unserialize(JSONObject data, EntityManager em) {
        Album album = new Album();
        album.setAlbTitle(data.getString("albTitle"));
        album.setAlbIntro(data.getString("albIntro"));
        album.setGrade(data.getString("grade"));
        
        if (!data.getString("inKlass").equals("")) {
            Query qklass = em.createNamedQuery("Klass.findByKId");
            qklass.setParameter("kId", data.getInteger("inKlass"));
            List<Klass> klasses = qklass.getResultList();
            if (klasses.size() > 0) {
                album.setInKlass(klasses.get(0));        
            }
        }
        return album;
    }
}
