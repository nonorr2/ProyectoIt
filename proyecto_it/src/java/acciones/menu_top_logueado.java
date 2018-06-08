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
import WS.Tematica;
import WS.TematicaWS;
import WS.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
import WS.UsuarioWS;
import WS.VotoPublicacionWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Nono
 */
public class menu_top_logueado extends ActionSupport {

    List<Chat> chats;
    List<Usuario> usuarios;
    Integer id_user;

//    private List<PublicacionDecorado> misPublicaciones;
    private List<PublicacionDecorado> listaPublicaciones;
    private List<Tematica> tematicas;
//    private List<PublicacionDecorado> publicacionesSuscrito;

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
        id_user = usuario.getId();

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
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {
        };

        PublicacionWS publicacionCliente = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();

        List<Publicacion> publicaciones = (List<Publicacion>) publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, String.valueOf(usuario.getId()));
        this.listaPublicaciones = new ArrayList<PublicacionDecorado>();

        for (Publicacion publicacion : publicaciones) {
            PublicacionDecorado publicacionDecorado = new PublicacionDecorado();
            Long numComentarios = comentarioCliente.getNumComentarios(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosPositivos = votoPublicacionCliente.getVotosPositivos(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosNegativos = votoPublicacionCliente.getVotosNegativos(tipoLong, String.valueOf(publicacion.getId()));
            publicacionDecorado.setPublicacion(publicacion);
            publicacionDecorado.setNumComentarios(numComentarios.intValue());
            publicacionDecorado.setNumVotosPositivosPublicacion(numVotosPositivos.intValue());
            publicacionDecorado.setNumVotosNegativosPublicacion(numVotosNegativos.intValue());
            this.listaPublicaciones.add(publicacionDecorado);
        }

        //Listar las temáticas para el selec de añadir piblicacion
        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.findAll_XML(tipoTematica);
        
        return SUCCESS;
    }

    public String home() throws Exception {
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        GenericType<Comentario> tipoComentario = new GenericType<Comentario>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {
        };
        PublicacionWS publicacionCliente = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();

        List<Publicacion> publicaciones = publicacionCliente.getPublicacionesSuscritoOrdenadoNumComentarios_XML(tipoPublicacion, String.valueOf(usuario.getId()));
        this.listaPublicaciones = new ArrayList<PublicacionDecorado>();

        for (Publicacion publicacion : publicaciones) {
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
            this.listaPublicaciones.add(publicacionDecorado);
        }

        return SUCCESS;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
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

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public List<PublicacionDecorado> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<PublicacionDecorado> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

}
