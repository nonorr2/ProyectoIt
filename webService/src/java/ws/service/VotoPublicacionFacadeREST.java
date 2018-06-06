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
import ws.VotoPublicacion;

/**
 *
 * @author Nono
 */
@Stateless
@Path("ws.votopublicacion")
public class VotoPublicacionFacadeREST extends AbstractFacade<VotoPublicacion> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public VotoPublicacionFacadeREST() {
        super(VotoPublicacion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(VotoPublicacion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, VotoPublicacion entity) {
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
    public VotoPublicacion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<VotoPublicacion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<VotoPublicacion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Metodo para obtener el número de votos negativos de una publicación. tipo
     * (0) --> Votos negativo
     *
     * @param id
     * @return número de votos
     */
    @GET
    @Path("/getVotosNegativos/{idPublicacion}")
    @Produces({MediaType.TEXT_PLAIN})
    public Long getVotosNegativos(@PathParam("idPublicacion") Integer id) {
        Publicacion publicacion = getPublicacion(id);
        Query query = em.createQuery("SELECT COUNT(v.idPublicacion) FROM VotoPublicacion v WHERE v.idPublicacion = :publicacion AND v.tipo = 0");
        query.setParameter("publicacion", publicacion);
        Long result = (Long) query.getSingleResult();
        return result;
    }

    /**
     * Metodo para obtener el número de votos positivos de una publicación. tipo
     * (1) --> Votos positivos
     *
     * @param id
     * @return número de votos
     */
    @GET
    @Path("/getVotosPositivos/{idPublicacion}")
    @Produces({MediaType.TEXT_PLAIN})
    public Long getVotosPositivos(@PathParam("idPublicacion") Integer id) {
        Publicacion publicacion = getPublicacion(id);
        Query query = em.createQuery("SELECT COUNT(v.idPublicacion) FROM VotoPublicacion v WHERE v.idPublicacion = :publicacion AND v.tipo = 1");
        query.setParameter("publicacion", publicacion);
        Long result = (Long) query.getSingleResult();
        return result;
    }

    /**
     * Devuelve una publicacion que coincide con el id pasado como parámetro
     *
     * @param id
     * @return Publicacion
     */
    private Publicacion getPublicacion(Integer id) {
        Query query = em.createQuery("SELECT p FROM Publicacion p WHERE p.id = :id");
        query.setParameter("id", id);
        Publicacion p = (Publicacion) query.getSingleResult();
        return p;
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
     * Método para obtener un voto de la publicación a partir del identificador
     * del usurio y la publicación
     *
     * @param idUsuario
     * @param idPublicacion
     * @return
     */
    @GET
    @Path("/getVotoPublicacion/{idUsuario}/{idPublicacion}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public VotoPublicacion getVotoPublicacion(@PathParam("idUsuario") Integer idUsuario, @PathParam("idPublicacion") Integer idPublicacion) {
        Publicacion publicacion = this.getPublicacion(idPublicacion);
        Usuario usuario = this.getUsuario(idUsuario);
        Query query = em.createQuery("SELECT vp FROM VotoPublicacion vp WHERE vp.idUsuario = :usuario AND vp.idPublicacion = :publicacion");
        query.setParameter("usuario", usuario);
        query.setParameter("publicacion", publicacion);
        VotoPublicacion votoPublicacion = (VotoPublicacion) query.getSingleResult();
        return votoPublicacion;
    }

    /**
     * Método para comprobar si ya se ha votado la publicación.
     *
     * @param idUsuario
     * @param idPublicacion
     * @return
     */
    @GET
    @Path("/existeVotoPublicacion/{idUsuario}/{idPublicacion}")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean existeVotoPublicacion(@PathParam("idUsuario") Integer idUsuario, @PathParam("idPublicacion") Integer idPublicacion) {
        boolean encontrado = false;
        Publicacion publicacion = this.getPublicacion(idPublicacion);
        Usuario usuario = this.getUsuario(idUsuario);
        Query query = em.createQuery("SELECT vp FROM VotoPublicacion vp WHERE vp.idUsuario = :usuario AND vp.idPublicacion = :publicacion");
        query.setParameter("usuario", usuario);
        query.setParameter("publicacion", publicacion);

        try {
            VotoPublicacion votoPublicacion = (VotoPublicacion) query.getSingleResult();

            if (votoPublicacion != null) {
                encontrado = true;
            }
        } catch (Exception e) {
            encontrado = false;
        }

        return encontrado;
    }

}
