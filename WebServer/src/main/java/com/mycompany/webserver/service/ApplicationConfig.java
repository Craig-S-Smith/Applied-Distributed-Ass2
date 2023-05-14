/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webserver.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author diamo
 */
@javax.ws.rs.ApplicationPath("webresources")
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
        resources.add(com.mycompany.webserver.NEMAService.class);
        resources.add(com.mycompany.webserver.service.DroneFacadeREST.class);
        resources.add(com.mycompany.webserver.service.FireFacadeREST.class);
        resources.add(com.mycompany.webserver.service.FiretrucksFacadeREST.class);
        resources.add(org.eclipse.persistence.jaxb.rs.MOXyJsonProvider.class);
    }
    
}
