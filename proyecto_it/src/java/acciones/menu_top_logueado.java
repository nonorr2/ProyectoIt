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
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import WS.UsuarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Nono
 */
public class menu_top_logueado extends ActionSupport {

    List<Chat> chats;
    List<Usuario> usuarios;

    private List<Publicacion> misPublicaciones;
    private List<Tematica> tematicas;

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

    /**
     * Método para lista todas las publiaciones que ha creado el usuario
     * logueado.
     *
     * @return
     * @throws Exception
     */
    public String misPubicaciones() throws Exception {
        loginLogout.session = (Map) ActionContext.getContext().get("session");
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        PublicacionWS publicacionCliente = new PublicacionWS();
        this.misPublicaciones = (List<Publicacion>) publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, String.valueOf(usuario.getId()));

        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.getTematicasMasPopulares_JSON(tipoTematica);
        return SUCCESS;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<Publicacion> getMisPublicaciones() {
        return misPublicaciones;
    }

    public void setMisPublicaciones(List<Publicacion> misPublicaciones) {
        this.misPublicaciones = misPublicaciones;
    }

    public List<Tematica> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<Tematica> tematicas) {
        this.tematicas = tematicas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
