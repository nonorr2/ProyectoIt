/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

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
import javax.ws.rs.core.MediaType;
import ws.Publicacion;
import ws.Usuario;

/**
 *
 * @author David
 */
@Stateless
@Path("ws.publicacion")
public class PublicacionFacadeREST extends AbstractFacade<Publicacion> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public PublicacionFacadeREST() {
        super(Publicacion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Publicacion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Publicacion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Publicacion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("/getPublicacionPorTitulo/{titulo}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Publicacion> getPublicacionPorTitulo(@PathParam("titulo") String titulo) {
        String jpql = "SELECT p FROM Publicacion p WHERE p.titulo LIKE '" + titulo + "%'";
        Query query = em.createQuery(jpql);
        List<Publicacion> result = query.getResultList();
        return result;
    }
    
    /**
     * Método auxiliar para obtener un usuario a partir del identificardor.
     * @param idUsuario
     * @return un objeto usario
     */
    private Usuario getUsuario(@PathParam("idUsuario") Integer idUsuario) {
        String jpql = "SELECT u FROM Usuario u WHERE u.id = :idUsuario";        
        Query query = em.createQuery(jpql); 
        query.setParameter("idUsuario", idUsuario);
        Usuario usuario = (Usuario) query.getSingleResult();
        return usuario;
    }    
    
    /**
     * Método para obtener todas las publicaciones de un usuario,
     * ordenandas de forma descendente.
     * @param idUsuario
     * @return una lista de publicaciones
     */
    @GET
    @Path("/getMisPublicaciones/{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> getMisPublicaciones(@PathParam("idUsuario") Integer idUsuario) {
        Usuario usuario = this.getUsuario(idUsuario);
        String jpql = "SELECT p FROM Publicacion p WHERE p.idUsuario = :usuario ORDER BY p.fechaHoraModificacion DESC";        
        Query query = em.createQuery(jpql); 
        query.setParameter("usuario", usuario);
        List<Publicacion> publicaciones = query.getResultList(); 
        return publicaciones;
    }
}
