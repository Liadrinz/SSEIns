/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ahuan
 */
@javax.ws.rs.ApplicationPath("rest-api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.alibaba.fastjson.support.jaxrs.FastJsonProvider.class);
        resources.add(com.monkey.backends1.service.AlbumFacadeREST.class);
        resources.add(com.monkey.backends1.service.CommentFacadeREST.class);
        resources.add(com.monkey.backends1.service.FilesseFacadeREST.class);
        resources.add(com.monkey.backends1.service.KlassFacadeREST.class);
        resources.add(com.monkey.backends1.service.NoticeFacadeREST.class);
        resources.add(com.monkey.backends1.service.StudentFacadeREST.class);
    }
    
}
