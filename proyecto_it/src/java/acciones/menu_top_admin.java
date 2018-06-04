/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Chat;
import WS.ChatWS;
import WS.Publicacion;
import WS.PublicacionWS;
import WS.Tematica;
import WS.TematicaWS;
import WS.Usuario;
import WS.UsuarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class menu_top_admin extends ActionSupport {

    private List<Usuario> usuarios;
    private List<Tematica> tematicas;
    private List<Publicacion> publicaciones;
    private List<Chat> chats;

    public menu_top_admin() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String usuarios() throws Exception {
        GenericType<List<Usuario>> tipoUsuarios = new GenericType<List<Usuario>>() {
        };
        UsuarioWS usuarioClient = new UsuarioWS();
        usuarios = usuarioClient.getUsuarioTipo_JSON(tipoUsuarios);
        return SUCCESS;
    }

    public String publicacionesAdmin() throws Exception {
        GenericType<List<Publicacion>> tipoPublicacionAdmin = new GenericType<List<Publicacion>>() {
        };

        PublicacionWS publicacionClient = new PublicacionWS();
        publicaciones = publicacionClient.findAll_JSON(tipoPublicacionAdmin);
        return SUCCESS;
    }

    public String tematicasAdmin() throws Exception {
        GenericType<List<Tematica>> tipoTematicaaAdmin = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = tematicaClient.findAll_JSON(tipoTematicaaAdmin);
        return SUCCESS;
    }
    
    public String chatsAdmin() throws Exception {
        GenericType<List<Chat>> tipoChat = new GenericType<List<Chat>>(){};
        ChatWS chatClient = new ChatWS();
        chats = chatClient.findAll_JSON(tipoChat);
        return SUCCESS;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Tematica> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<Tematica> tematicas) {
        this.tematicas = tematicas;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }
    
}
