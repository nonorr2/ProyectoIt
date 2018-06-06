/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Chat;
import WS.ChatWS;
import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionDecorado;
import WS.PublicacionWS;
import WS.Tematica;
import WS.TematicaDecorado;
import WS.TematicaWS;
import WS.Usuario;
import WS.UsuarioWS;
import WS.VotoPublicacionWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class menu_top_admin extends ActionSupport {

    private List<Usuario> usuarios;
    private List<TematicaDecorado> tematicas;
    private List<PublicacionDecorado> publicaciones;
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
        GenericType<Long> tipoLong = new GenericType<Long>() {};

        PublicacionWS publicacionClient = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        
        List<Publicacion> listPublicaciones = publicacionClient.findAll_XML(tipoPublicacionAdmin);
        this.publicaciones = new ArrayList<PublicacionDecorado>();
        
        for (Publicacion publicacion : listPublicaciones) {
            PublicacionDecorado publicacionDecorado = new PublicacionDecorado();
            Long numComentarios = comentarioCliente.getNumComentarios(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosPositivos = votoPublicacionCliente.getVotosPositivos(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosNegativos = votoPublicacionCliente.getVotosNegativos(tipoLong, String.valueOf(publicacion.getId()));
            publicacionDecorado.setPublicacion(publicacion);
            publicacionDecorado.setNumComentarios(numComentarios.intValue());
            publicacionDecorado.setNumVotosPositivosPublicacion(numVotosPositivos.intValue());
            publicacionDecorado.setNumVotosNegativosPublicacion(numVotosNegativos.intValue());
            this.publicaciones.add(publicacionDecorado);
        }
        
        return SUCCESS;
    }

    public String tematicasAdmin() throws Exception {
        GenericType<List<Tematica>> tipoTematicaaAdmin = new GenericType<List<Tematica>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>(){};
                
        TematicaWS tematicaClient = new TematicaWS();
        PublicacionWS publicacionClient = new PublicacionWS();
        
        List<Tematica> listTematica = tematicaClient.findAll_XML(tipoTematicaaAdmin);
        this.tematicas = new ArrayList<TematicaDecorado>();
        
        for (Tematica tematica : listTematica) {
            TematicaDecorado tematicaDecorado = new TematicaDecorado();
            Long numPubliTema = publicacionClient.getNumPublicacionesByTematica(tipoLong, String.valueOf(tematica.getId()));
            tematicaDecorado.setTematicas(tematica);
            tematicaDecorado.setNumPublicacionesDeUnaTematica(numPubliTema.intValue());
            this.tematicas.add(tematicaDecorado);
        }
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

    public List<TematicaDecorado> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<TematicaDecorado> tematicas) {
        this.tematicas = tematicas;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<PublicacionDecorado> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionDecorado> publicaciones) {
        this.publicaciones = publicaciones;
    }
    
}
