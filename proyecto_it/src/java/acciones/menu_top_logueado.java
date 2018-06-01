/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Chat;
import WS.ChatWS;
import WS.Usuario;
import WS.UsuarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Nono
 */
public class menu_top_logueado extends ActionSupport {

    List<Chat> chats;
    List<Usuario> usuarios;

    public menu_top_logueado() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String chats() throws Exception {
        GenericType<List<Chat>> tipoChat = new GenericType<List<Chat>>() {
        };
        GenericType<List<Usuario>> tipoUsuario = new GenericType<List<Usuario>>() {
        };
        ChatWS chatClient = new ChatWS();
        UsuarioWS usuarioClient = new UsuarioWS();

        Usuario usuario = (Usuario) loginLogout.session.get("user");
        chats = chatClient.getChatsUsuario_JSON(tipoChat, String.valueOf(usuario.getId()));
        usuarios = usuarioClient.findAll_JSON(tipoUsuario);
        
        return SUCCESS;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
