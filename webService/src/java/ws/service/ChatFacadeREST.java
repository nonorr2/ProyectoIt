/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.ArrayList;
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
import ws.Usuario;
import ws.UsuarioChat;

/**
 *
 * @author David
 */
@Stateless
@Path("ws.chat")
public class ChatFacadeREST extends AbstractFacade<Chat> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public ChatFacadeREST() {
        super(Chat.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Chat entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Chat entity) {
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
    public Chat find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Devuelve los chats de los que es participante el usuario con id pasado
     * como parámetro
     *
     * @param id_user
     * @return List<Chat>
     */
    @GET
    @Path("/getChatsUsuario/{id_user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> getChatsUsuario(@PathParam("id_user") Integer id_user) {
        Usuario usu = getUserById(id_user);
        Query query = em.createQuery("SELECT uc FROM UsuarioChat uc WHERE uc.usuario = :usuario");
        query.setParameter("usuario", usu);
        List<UsuarioChat> usuario_chat = query.getResultList();
        List<Chat> chats = new ArrayList<Chat>();

        for (UsuarioChat uc : usuario_chat) {
            chats.add(uc.getChat());
        }
        
        return chats;
    }

    /**
     * Devuelve un usuario que coincide con el id pasado como parámetro
     *
     * @param id_user
     * @return Usuario
     */
    private Usuario getUserById(Integer id_user) {
        Query qUsuario = em.createQuery("SELECT u FROM Usuario u WHERE u.id= :id_usuario");
        qUsuario.setParameter("id_usuario", id_user);
        Usuario usu = (Usuario) qUsuario.getSingleResult();
        return usu;
    }
    
}
