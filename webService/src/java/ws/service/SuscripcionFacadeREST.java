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
import ws.Suscripcion;
import ws.SuscripcionPK;
import ws.Usuario;

/**
 *
 * @author David
 */
@Stateless
@Path("ws.suscripcion")
public class SuscripcionFacadeREST extends AbstractFacade<Suscripcion> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    private SuscripcionPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idUsuario=idUsuarioValue;idPublicacion=idPublicacionValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        ws.SuscripcionPK key = new ws.SuscripcionPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idUsuario = map.get("idUsuario");
        if (idUsuario != null && !idUsuario.isEmpty()) {
            key.setIdUsuario(new java.lang.Integer(idUsuario.get(0)));
        }
        java.util.List<String> idPublicacion = map.get("idPublicacion");
        if (idPublicacion != null && !idPublicacion.isEmpty()) {
            key.setIdPublicacion(new java.lang.Integer(idPublicacion.get(0)));
        }
        return key;
    }

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
    public void edit(@PathParam("id") PathSegment id, Suscripcion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        ws.SuscripcionPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Suscripcion find(@PathParam("id") PathSegment id) {
        ws.SuscripcionPK key = getPrimaryKey(id);
        return super.find(key);
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
     * Método para obtener todas las publicaciones en las que el usuario esta subcrito.
     * @param idUsuario
     * @return lista de subcripcion
     */
    @GET
    @Path("/getPublicacionSuscrito/{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscripcion> getPublicacionSuscrito(@PathParam("idUsuario") Integer idUsuario) {
        Usuario usuario = this.getUsuario(idUsuario);
        String jpql = "SELECT s FROM Suscripcion s WHERE s.usuario = :usuario";        
        Query query = em.createQuery(jpql); 
        query.setParameter("usuario", usuario);
        List<Suscripcion> suscripciones = query.getResultList(); 
        return suscripciones;
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
    
}
