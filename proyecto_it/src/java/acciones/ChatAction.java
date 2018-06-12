/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Chat;
import WS.ChatWS;
import WS.Usuario;
import WS.UsuarioChat;
import WS.UsuarioChatWS;
import WS.UsuarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class ChatAction extends ActionSupport {

    String nombreChat;
    String usuario;
    String idChat;
    String idChatEdit;

    List<Chat> chats;

    public ChatAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método que hace uso del Web Service para crear un nuevo chat entre dos
     * personas.
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String crearChat() throws Exception {
        GenericType<Usuario> tipoUsuario = new GenericType<Usuario>() {
        };
        GenericType<Chat> tipoChat = new GenericType<Chat>() {
        };
        UsuarioWS usuarioClient = new UsuarioWS();
        ChatWS chatClient = new ChatWS();
        UsuarioChatWS usuarioChatClient = new UsuarioChatWS();

        Usuario usu = usuarioClient.find_XML(tipoUsuario, usuario);
        Chat chat = new Chat();
        chat.setFechaHora(new Date());
        chat.setNombre(nombreChat);

        chatClient.create_JSON(chat);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        chat = chatClient.getChatByNameAndTime_JSON(tipoChat, nombreChat, sdf.format(chat.getFechaHora()));

        UsuarioChat usuChat1 = new UsuarioChat(Integer.parseInt(usuario), chat.getId());
        usuChat1.setUsuario(usu);
        usuChat1.setChat(chat);
        usuChat1.setFecha(new Date());
        usuarioChatClient.create_XML(usuChat1);

        Usuario userActivo = (Usuario) loginLogout.session.get("user");

        UsuarioChat usuChat2 = new UsuarioChat(userActivo.getId(), chat.getId());
        usuChat2.setUsuario(userActivo);
        usuChat2.setChat(chat);
        usuChat2.setFecha(new Date());
        usuarioChatClient.create_XML(usuChat2);
        return SUCCESS;
    }

    /**
     * Método que hace uso del Web Service para borrar un chat
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String borrarChat() throws Exception {
        ChatWS chatClient = new ChatWS();

        chatClient.remove(idChat);
        return SUCCESS;
    }

    /**
     * Método que hace uso del Web Service para editar un chat
     *
     * @return
     * @throws Exception
     */
    public String editarChat() throws Exception {
        ChatWS chatClient = new ChatWS();
        GenericType<Chat> tipoChat = new GenericType<Chat>() {
        };
        Chat chat = chatClient.find_XML(tipoChat, idChatEdit);
        chat.setNombre(nombreChat);
        chatClient.edit_XML(chat, idChatEdit);
        return SUCCESS;
    }

    public String getNombreChat() {
        return nombreChat;
    }

    public void setNombreChat(String nombreChat) {
        this.nombreChat = nombreChat;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public String getIdChatEdit() {
        return idChatEdit;
    }

    public void setIdChatEdit(String idChatEdit) {
        this.idChatEdit = idChatEdit;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

}
