/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Chat;
import WS.ChatWS;
import WS.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Nono
 */
public class menu_top_logueado extends ActionSupport {

    List<Chat> chats;

    public menu_top_logueado() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String chats() throws Exception {
        GenericType<List<Chat>> tipoChat = new GenericType<List<Chat>>() {
        };
        ChatWS chatClient = new ChatWS();

        Usuario usuario = (Usuario) loginLogout.session.get("user");
        chats = chatClient.getChatsUsuario_JSON(tipoChat, String.valueOf(usuario.getId()));
        return SUCCESS;
    }

}
