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
import javax.ws.rs.core.GenericType;

public class ChatAction extends ActionSupport {

    String nombreChat;
    String usuario;
    String idChat;

    public ChatAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

    public String borrarChat() throws Exception {
        ChatWS chatClient = new ChatWS();

        chatClient.remove(idChat);
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

}