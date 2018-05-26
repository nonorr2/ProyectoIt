/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author David
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
        resources.add(ws.service.ChatFacadeREST.class);
        resources.add(ws.service.ComentarioFacadeREST.class);
        resources.add(ws.service.MensajeFacadeREST.class);
        resources.add(ws.service.PublicacionFacadeREST.class);
        resources.add(ws.service.SuscripcionFacadeREST.class);
        resources.add(ws.service.TematicaFacadeREST.class);
        resources.add(ws.service.UsuarioChatFacadeREST.class);
        resources.add(ws.service.UsuarioFacadeREST.class);
        resources.add(ws.service.VotoComentarioFacadeREST.class);
        resources.add(ws.service.VotoPublicacionFacadeREST.class);
    }
    
}
