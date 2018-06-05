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
import ws.VotoComentario;

/**
 *
 * @author Nono
 */
@Stateless
@Path("ws.votocomentario")
public class VotoComentarioFacadeREST extends AbstractFacade<VotoComentario> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public VotoComentarioFacadeREST() {
        super(VotoComentario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(VotoComentario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, VotoComentario entity) {
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
    public VotoComentario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<VotoComentario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<VotoComentario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    /**
     * Devuelve el numero de votos positivos que tiene el comentario con el id
     * pasado como parámetro
     *
     * @param id_comentario
     * @return Long
     */
    @GET
    @Path("/getVotosPositivos/{id_comentario}")
    @Produces(MediaType.TEXT_PLAIN)
    public Long getVotosPositivos(@PathParam("id_comentario") Integer id_comentario) {
        Comentario comentario = getComentarioById(id_comentario);
        Query query = em.createQuery("SELECT COUNT(vc.fechaHora) votos FROM VotoComentario vc WHERE vc.idComentario =  :comentario AND vc.tipo=1");
        query.setParameter("comentario", comentario);
        Long votos = (Long) query.getSingleResult();
        return votos;
    }

    /**
     * Devuelve el numero de votos positivos que tiene el comentario con el id
     * pasado como parámetro
     *
     * @param id_comentario
     * @return Long
     */
    @GET
    @Path("/getVotosNegativos/{id_comentario}")
    @Produces(MediaType.TEXT_PLAIN)
    public Long getVotosNegativos(@PathParam("id_comentario") Integer id_comentario) {
        Comentario comentario = getComentarioById(id_comentario);
        Query query = em.createQuery("SELECT COUNT(vc.fechaHora) votos FROM VotoComentario vc WHERE vc.idComentario = :comentario AND vc.tipo=0");
        query.setParameter("comentario", comentario);
        Long votos = (Long) query.getSingleResult();
        return votos;
    }

    /**
     * Devuelve un Comentario que coincide con el id pasado como parámetro
     *
     * @param id_comentario
     * @return Comentario
     */
    private Comentario getComentarioById(Integer id_comentario) {
        Query q = em.createQuery("SELECT c FROM Comentario c WHERE c.id= :comentario");
        q.setParameter("comentario", id_comentario);
        return (Comentario) q.getSingleResult();
    }
}
