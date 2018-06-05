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
import ws.Chat;
import ws.Mensaje;

/**
 *
 * @author Nono
 */
@Stateless
@Path("ws.mensaje")
public class MensajeFacadeREST extends AbstractFacade<Mensaje> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public MensajeFacadeREST() {
        super(Mensaje.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Mensaje entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Mensaje entity) {
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
    public Mensaje find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mensaje> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mensaje> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Devuelve los mensajes que pertenecen al chat con el id pasado com
     * parámetro
     *
     * @param id_chat
     * @return List<Mensaje>
     */
    @GET
    @Path("/getMensajesChat/{id_chat}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mensaje> getMensajesChat(@PathParam("id_chat") Integer id_chat) {
        Chat chat = getChatById(id_chat);
        Query query = em.createQuery("SELECT m FROM Mensaje m WHERE m.idChat = :chat");
        query.setParameter("chat", chat);
        List<Mensaje> mensajes = query.getResultList();
        return mensajes;
    }

    /**
     * Devuelve un chat cuyo id corresponde con el id pasado como parámero
     *
     * @param id_chat
     * @return Chat
     */
    public Chat getChatById(Integer id_chat) {
        Query query = em.createQuery("SELECT c FROM Chat c WHERE c.id = :chat");
        query.setParameter("chat", id_chat);
        Chat chat = (Chat) query.getSingleResult();
        return chat;
    }
}
