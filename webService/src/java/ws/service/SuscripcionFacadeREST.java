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
import ws.Suscripcion;
import ws.Usuario;

/**
 *
 * @author Nono
 */
@Stateless
@Path("ws.suscripcion")
public class SuscripcionFacadeREST extends AbstractFacade<Suscripcion> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public SuscripcionFacadeREST() {
        super(Suscripcion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Suscripcion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Suscripcion entity) {
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
    public Suscripcion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscripcion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscripcion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Método para obtener todas las publicaciones en las que el usuario esta
     * subcrito.
     *
     * @param idUsuario
     * @return lista de subcripcion
     */
    @GET
    @Path("/getPublicacionSuscrito/{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscripcion> getPublicacionSuscrito(@PathParam("idUsuario") Integer idUsuario) {
        Usuario usuario = this.getUsuario(idUsuario);
        Query query = em.createQuery("SELECT s FROM Suscripcion s WHERE s.idUsuario = :usuario");
        query.setParameter("usuario", usuario);
        List<Suscripcion> suscripciones = query.getResultList();
        return suscripciones;
    }

    /**
     * Método auxiliar para obtener un usuario a partir del identificardor.
     *
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
     * Método auxiliar para obtener una publicación a partir del identificardor.
     *
     * @param idPublicacion
     * @return un objeto usario
     */
    private Publicacion getPublicacion(@PathParam("idPublicacion") Integer idPublicacion) {
        Query query = em.createQuery("SELECT p FROM Publicacion p WHERE p.id = :idPublicacion");
        query.setParameter("idPublicacion", idPublicacion);
        Publicacion publicacion = (Publicacion) query.getSingleResult();
        return publicacion;
    }
    
    @GET
    @Path("/getSuscripcion/{idUsuario}/{idPublicacion}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Suscripcion getSuscripcion(@PathParam("idUsuario") Integer idUsuario, @PathParam("idPublicacion") Integer idPublicacion) {
        Publicacion publicacion = this.getPublicacion(idPublicacion);
        Usuario usuario = this.getUsuario(idUsuario);
        Query query = em.createQuery("SELECT s FROM Suscripcion s WHERE s.idUsuario = :usuario AND s.idPublicacion = :publicacion");
        query.setParameter("usuario", usuario);
        query.setParameter("publicacion", publicacion);
        Suscripcion suscripcion = (Suscripcion) query.getSingleResult();
        return suscripcion;
    }
}
