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
import ws.Tematica;

/**
 *
 * @author Nono
 */
@Stateless
@Path("ws.tematica")
public class TematicaFacadeREST extends AbstractFacade<Tematica> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public TematicaFacadeREST() {
        super(Tematica.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tematica entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tematica entity) {
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
    public Tematica find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tematica> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tematica> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Metodo para obtener las tematicas cuyo nombre corresponda con el nombre
     * pasado como parametro
     *
     * @param nombre
     * @return Lista de tematicas
     */
    @GET
    @Path("/getTematicasPorNombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tematica> getTematicasPorNombre(@PathParam("nombre") String nombre) {
        String jpql = "SELECT t FROM Tematica t WHERE t.nombre LIKE '" + nombre + "%'";
        Query query = em.createQuery(jpql);
        List<Tematica> result = query.getResultList();
        return result;
    }

    @GET
    @Path("getTematicasMasPopulares")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tematica> getTematicasMasPopulares() {
        Query query = em.createQuery("SELECT t FROM Publicacion p, Tematica t WHERE p.idTematica=t GROUP BY t.id").setMaxResults(8);
        List<Tematica> result = query.getResultList();
        return result;
    }

    /**
     * Metodo para obtener las tematicas cuyo nombre corresponda con el nombre
     * pasado como parametro
     *
     * @param nombre
     * @return Lista de tematicas
     */
    @GET
    @Path("/encontrarTematica/{nombre}")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean encontrarTematica(@PathParam("nombre") String nombre) {
        Boolean enc = true;
        Query query = em.createQuery("SELECT t FROM Tematica t WHERE t.nombre = :nombre").setParameter("nombre", nombre);
        Tematica result;
        try {
            result = (Tematica) query.getSingleResult();
        } catch (Exception e) {
            enc = false;
        }
        return enc;
    }
}
