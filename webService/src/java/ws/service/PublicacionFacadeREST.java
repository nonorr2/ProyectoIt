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
import ws.Tematica;

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
     * Devuelve una listado de publicaciones perteneciente a la emática que
     * coincide con el id pasado como parámetro
     *
     * @param id_tematica
     * @return List<Publicacion>
     */
    @GET
    @Path("/getPublicacionesByTematica/{id_tematica}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> getPublicacionesByTema(@PathParam("id_tematica") Integer id_tematica) {
        Tematica tematica = getTematicaById(id_tematica);
        Query query = em.createQuery("SELECT p FROM Publicacion p WHERE p.idTematica = :tematica");
        query.setParameter("tematica", tematica);
        List<Publicacion> publicaciones = query.getResultList();
        return publicaciones;
    }

    /**
     * Devuelve una temática que coincide con el id pasado como parámetro
     *
     * @param id_tematica
     * @return Tematica
     */
    private Tematica getTematicaById(Integer id_tematica) {
        Query q = em.createQuery("Select t FROM Tematica t WHERE t.id = :tematica");
        q.setParameter("tematica", id_tematica);
        return (Tematica) q.getSingleResult();
    }
}
