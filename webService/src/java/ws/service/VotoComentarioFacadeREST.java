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
import javax.ws.rs.core.PathSegment;
import ws.Comentario;
import ws.VotoComentario;
import ws.VotoComentarioPK;

/**
 *
 * @author David
 */
@Stateless
@Path("ws.votocomentario")
public class VotoComentarioFacadeREST extends AbstractFacade<VotoComentario> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    private VotoComentarioPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idUsuario=idUsuarioValue;idComentario=idComentarioValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        ws.VotoComentarioPK key = new ws.VotoComentarioPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idUsuario = map.get("idUsuario");
        if (idUsuario != null && !idUsuario.isEmpty()) {
            key.setIdUsuario(new java.lang.Integer(idUsuario.get(0)));
        }
        java.util.List<String> idComentario = map.get("idComentario");
        if (idComentario != null && !idComentario.isEmpty()) {
            key.setIdComentario(new java.lang.Integer(idComentario.get(0)));
        }
        return key;
    }

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
    public void edit(@PathParam("id") PathSegment id, VotoComentario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        ws.VotoComentarioPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public VotoComentario find(@PathParam("id") PathSegment id) {
        ws.VotoComentarioPK key = getPrimaryKey(id);
        return super.find(key);
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
        Query query = em.createQuery("SELECT COUNT(vc.fechaHora) votos FROM VotoComentario vc WHERE vc.comentario= :comentario AND vc.tipo=1");
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
        Query query = em.createQuery("SELECT COUNT(vc.fechaHora) votos FROM VotoComentario vc WHERE vc.comentario= :comentario AND vc.tipo=0");
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
