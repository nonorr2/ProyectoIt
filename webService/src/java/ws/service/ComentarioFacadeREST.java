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
import ws.Comentario;
import ws.Publicacion;

/**
 *
 * @author David
 */
@Stateless
@Path("ws.comentario")
public class ComentarioFacadeREST extends AbstractFacade<Comentario> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public ComentarioFacadeREST() {
        super(Comentario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Comentario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Comentario entity) {
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
    public Comentario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    /**
     * Devuelve una lista de comentarios pertenecientes a una publicación que
     * coincide con el id pasado como parámetro
     *
     * @param id_publicacion
     * @return List<Comentario>
     */
    @GET
    @Path("/getComentariosPublicacion/{id_publicacion}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> getComentariosPublicacion(@PathParam("id_publicacion") Integer id_publicacion) {
        Publicacion publicacion = getPublicacionById(id_publicacion);
        Query query = em.createQuery("SELECT c FROM Comentario c WHERE c.idPublicacion = :publicacion");
        query.setParameter("publicacion", publicacion);
        List<Comentario> comentarios = query.getResultList();
        return comentarios;
    }

    /**
     * Devuelve una publicación que coincide con el id pasado como parámetro
     *
     * @param id_publicacion
     * @return Publicacion
     */
    private Publicacion getPublicacionById(Integer id_publicacion) {
        Query q = em.createQuery("Select p FROM Publicacion p WHERE p.id= :publicacion");
        q.setParameter("publicacion", id_publicacion);
        Publicacion p = (Publicacion) q.getSingleResult();
        return p;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
