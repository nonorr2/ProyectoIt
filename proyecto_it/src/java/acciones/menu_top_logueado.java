/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Chat;
import WS.ChatWS;
import WS.Comentario;
import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionDecorado;
import WS.PublicacionWS;
import WS.Suscripcion;
import WS.SuscripcionWS;
import WS.Tematica;
import WS.TematicaWS;
import WS.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import WS.UsuarioWS;
import WS.VotoPublicacion;
import WS.VotoPublicacionWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
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

    private List<PublicacionDecorado> misPublicaciones;
    private List<Tematica> tematicas;
    private List<PublicacionDecorado> publicacionesSuscrito;

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
     * Método para listar todas las publiaciones que ha creado el usuario
     * logueado.
     *
     * @return
     * @throws Exception
     */
    public String misPubicaciones() throws Exception {
        loginLogout.session = (Map) ActionContext.getContext().get("session");
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {};
        GenericType<Long> tipoLong = new GenericType<Long>() {};
        
        PublicacionWS publicacionCliente = new PublicacionWS();        
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        
        List<Publicacion> publicaciones = (List<Publicacion>) publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, String.valueOf(usuario.getId()));
        this.misPublicaciones = new ArrayList<PublicacionDecorado>();
        
        for(Publicacion publicacion : publicaciones){
            PublicacionDecorado publicacionDecorado = new PublicacionDecorado();
            Long numComentarios = comentarioCliente.getNumComentarios(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosPositivos = votoPublicacionCliente.getVotosPositivos(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosNegativos = votoPublicacionCliente.getVotosNegativos(tipoLong, String.valueOf(publicacion.getId()));
            publicacionDecorado.setPublicacion(publicacion);
            publicacionDecorado.setNumComentarios(numComentarios.intValue());
            publicacionDecorado.setNumVotosPositivosPublicacion(numVotosPositivos.intValue());
            publicacionDecorado.setNumVotosNegativosPublicacion(numVotosNegativos.intValue());
            this.misPublicaciones.add(publicacionDecorado);
        }
        
        //Listar las temáticas para el selec de añadir piblicacion
        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {};
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.getTematicasMasPopulares_JSON(tipoTematica);
        
        return SUCCESS;
    }
    
    public String home() throws Exception{
        loginLogout.session = (Map) ActionContext.getContext().get("session");
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {};
        GenericType<Comentario> tipoComentario = new GenericType<Comentario>() {};
        GenericType<Long> tipoLong = new GenericType<Long>() {};
        PublicacionWS publicacionCliente = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        
        List<Publicacion> publicaciones = publicacionCliente.getPublicacionesSuscritoOrdenadoNumComentarios_XML(tipoPublicacion, String.valueOf(usuario.getId()));
        this.publicacionesSuscrito = new ArrayList<PublicacionDecorado>();
        
        for(Publicacion publicacion : publicaciones){
            PublicacionDecorado publicacionDecorado = new PublicacionDecorado();            
            Comentario comentario = comentarioCliente.getUltimoComentarioPublicacion_XML(tipoComentario, String.valueOf(publicacion.getId()));
            Long numComentarios = comentarioCliente.getNumComentarios(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosPositivos = votoPublicacionCliente.getVotosPositivos(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosNegativos = votoPublicacionCliente.getVotosNegativos(tipoLong, String.valueOf(publicacion.getId()));
            publicacionDecorado.setPublicacion(publicacion);
            publicacionDecorado.setUltimoComentario(comentario);
            publicacionDecorado.setNumComentarios(numComentarios.intValue());
            publicacionDecorado.setNumVotosPositivosPublicacion(numVotosPositivos.intValue());
            publicacionDecorado.setNumVotosNegativosPublicacion(numVotosNegativos.intValue());
            this.publicacionesSuscrito.add(publicacionDecorado);
        }
        
        return SUCCESS;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<PublicacionDecorado> getMisPublicaciones() {
        return misPublicaciones;
    }

    public void setMisPublicaciones(List<PublicacionDecorado> misPublicaciones) {
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

    public List<PublicacionDecorado> getPublicacionesSuscrito() {
        return publicacionesSuscrito;
    }

    public void setPublicacionesSuscrito(List<PublicacionDecorado> publicacionesSuscrito) {
        this.publicacionesSuscrito = publicacionesSuscrito;
    }
    
    

}
