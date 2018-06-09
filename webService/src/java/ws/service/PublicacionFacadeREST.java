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
import ws.Usuario;

/**
 *
 * @author Nono
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

    /**
     * Metodo para obtener las publicaciones cuyo titulo corresponda con el
     * titulo pasado como parametro
     *
     * @param titulo
     * @return Lista de publicaciones
     */
    @GET
    @Path("/getPublicacionPorTitulo/{titulo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> getPublicacionPorTitulo(@PathParam("titulo") String titulo) {
        String jpql = "SELECT p FROM Publicacion p WHERE p.titulo LIKE '%" + titulo + "%'";
        Query query = em.createQuery(jpql);
        List<Publicacion> result = query.getResultList();
        return result;
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
     * Método para obtener todas las publicaciones de un usuario, ordenandas de
     * forma descendente.
     *
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
     * Obtiene las publicaciones a las que está suscrito un usuario y ordenadas
     * descendentemente por número de comentarios.
     *
     * @param idUsuario
     * @return List<Publicacion>
     */
    @GET
    @Path("/getPublicacionesSuscritoOrdenadoNumComentarios/{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> getPublicacionesSuscritoOrdenadoNumComentarios(@PathParam("idUsuario") Integer idUsuario) {
        String sentencia = "select sub.id, sub.titulo, sub.contenido, " +
                            "sub.fecha_hora_creacion, sub.fecha_hora_modificacion, sub.ruta, " +
                            "sub.id_usuario, sub.id_tematica, sub.foto " +
                            "FROM(" +
                            "select count(*) comentarios, pub.* " +
                            "from publicacion pub " +
                            "left join comentario com on com.id_publicacion = pub.id " +
                            "inner join suscripcion sus on sus.id_publicacion = pub.id " +
                            "inner join usuario usu on sus.id_usuario = usu.id " +
                            "where usu.id = #usuario " +
                            "group by pub.id " +
                            "order by comentarios desc " +
                            ") sub";
        Query query = em.createNativeQuery(sentencia, Publicacion.class);
        query.setParameter("usuario", idUsuario);

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
    
    /**
     * Devuelve el número de publicaciones perteneciente a la temática que
     * coincide con el id pasado como parámetro
     *
     * @param id_tematica
     * @return List<Publicacion>
     */
    @GET
    @Path("/getNumPublicacionesByTematica/{id_tematica}")
    @Produces(MediaType.TEXT_PLAIN)
    public Long getNumPublicacionesByTematica(@PathParam("id_tematica") Integer id_tematica) {
        Tematica tematica = getTematicaById(id_tematica);
        Query query = em.createQuery("SELECT COUNT(p.idTematica) numPubli FROM Publicacion p WHERE p.idTematica = :tematica");
        query.setParameter("tematica", tematica);
        Long numPublicaciones = (Long) query.getSingleResult();
        return numPublicaciones;
    }
    
    /**
     * Metodo para obtener las publicaciones cuyo titulo corresponda con el
     * titulo pasado como parametro correspondientes a las publicaciones que
     * el usuario ha creado.
     * @param titulo
     * @return Lista de publicaciones
     */
    @GET
    @Path("/getMisPublicacionesPorTitulo/{titulo}/{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> getMisPublicacionesPorTitulo(@PathParam("titulo") String titulo, @PathParam("idUsuario") Integer idUsuario) {
        Usuario usuario = this.getUsuario(idUsuario);
        String jpql = "SELECT p FROM Publicacion p WHERE p.titulo LIKE '" + titulo + "%' AND p.idUsuario = :usuario";
        Query query = em.createQuery(jpql);
        query.setParameter("usuario", usuario);
        List<Publicacion> result = query.getResultList();
        return result;
    }
    
    @GET
    @Path("/getPublicacionesSuscritoPorTitulo/{idUsuario}/{titulo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Publicacion> getPublicacionesSuscritoPorTitulo(@PathParam("idUsuario") Integer idUsuario, @PathParam("titulo") String titulo) {
        String sentencia = "select sub.id, sub.titulo, sub.contenido, " +
                            "sub.fecha_hora_creacion, sub.fecha_hora_modificacion, sub.ruta, " +
                            "sub.id_usuario, sub.id_tematica, sub.foto " +
                            "FROM(" +
                            "select count(*) comentarios, pub.* " +
                            "from publicacion pub " +
                            "left join comentario com on com.id_publicacion = pub.id " +
                            "inner join suscripcion sus on sus.id_publicacion = pub.id " +
                            "inner join usuario usu on sus.id_usuario = usu.id " +
                            "where usu.id = #usuario and titulo like '%" + titulo + "%'" +
                            "group by pub.id " +
                            "order by comentarios desc " +
                            ") sub";
        Query query = em.createNativeQuery(sentencia, Publicacion.class);
        query.setParameter("usuario", idUsuario);

        List<Publicacion> publicaciones = query.getResultList();
        return publicaciones;
    }
}
