/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Nono
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

    @GET
    @Path("/getChatByNameAndTime/{name}/{time}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Chat getChatByNameAndTime(@PathParam("name") String name, @PathParam("time") String time) {
        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            date = format.parse(time);
        } catch (ParseException ex) {

        }
        Query query = em.createQuery("SELECT c FROM Chat c WHERE c.nombre = :name AND c.fechaHora= :time");
        query.setParameter("name", name);
        query.setParameter("time", date);
        Chat chat = (Chat) query.getSingleResult();
        return chat;
    }

    /**
     * Devuelve una lista de usuarios asociado al chat a un chat
     *
     * @param id_chat
     * @return Usuario
     */
    @GET
    @Path("/getUsuariosDeUnChat/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> getUsuariosDeUnChat(@PathParam("id") Integer id) throws Exception {
        Chat c = getChatById(id);
        Query query = em.createQuery("SELECT uc FROM UsuarioChat uc WHERE uc.chat =:chat");
        query.setParameter("chat", c);
        List<UsuarioChat> usuarioChat = query.getResultList();
        List<Usuario> usuarios = new ArrayList<Usuario>();

        for (UsuarioChat usu : usuarioChat) {
            usuarios.add(usu.getUsuario());
        }

        return usuarios;
    }

    /**
     * Devuelve un chat que coincide con el id pasado como parámetro
     *
     * @param id_chat
     * @return Usuario
     */
    private Chat getChatById(Integer id_chat) {
        Query qChat = em.createQuery("SELECT c FROM Chat c WHERE c.id= :id_chat");
        qChat.setParameter("id_chat", id_chat);
        Chat c = (Chat) qChat.getSingleResult();
        return c;
    }

    @GET
    @Path("/getChatPorNombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> getChatPorNombre(@PathParam("nombre") String nombre) {
        Query query = em.createQuery("SELECT c FROM Chat c WHERE c.nombre LIKE '" + nombre + "%'");
        List<Chat> result = query.getResultList();
        return result;
    }
}
