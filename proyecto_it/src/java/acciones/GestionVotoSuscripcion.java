/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Comentario;
import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionWS;
import WS.Suscripcion;
import WS.SuscripcionWS;
import WS.Usuario;
import WS.VotoComentario;
import WS.VotoComentarioWS;
import WS.VotoPublicacion;
import WS.VotoPublicacionWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Lydia
 */
public class GestionVotoSuscripcion extends ActionSupport {

    private String idPublicacion;
    private String idTema;

    public GestionVotoSuscripcion() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método que hace uso del Web Service para permite que un usuario vote
     * positivamente una publicación
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String votoPositivoPublicacion() throws Exception {
        Usuario usuario = (Usuario) loginLogout.session.get("user");
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        PublicacionWS publicacionCliente = new PublicacionWS();
        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {
        };
        GenericType<Boolean> tipoBoolean = new GenericType<Boolean>() {
        };
        GenericType<VotoPublicacion> tipoVotoPublicacion = new GenericType<VotoPublicacion>() {
        };
        Publicacion publicacion = publicacionCliente.find_XML(tipoPublicacion, idPublicacion);

        boolean encontrado = votoPublicacionCliente.existeVotoPublicacion(tipoBoolean, String.valueOf(usuario.getId()), idPublicacion);

        if (encontrado == false) {
            VotoPublicacion votoPublicacion = new VotoPublicacion(null, true, new Date());
            votoPublicacion.setIdPublicacion(publicacion);
            votoPublicacion.setIdUsuario(usuario);
            votoPublicacionCliente.create_XML(votoPublicacion);
        } else {
            VotoPublicacion votoPublicacion2 = votoPublicacionCliente.getVotoPublicacion_XML(tipoVotoPublicacion, String.valueOf(usuario.getId()), idPublicacion);
            if (votoPublicacion2.getTipo() == false) {
                votoPublicacion2.setTipo(true);
                votoPublicacionCliente.edit_XML(votoPublicacion2, String.valueOf(votoPublicacion2.getId()));
            }
        }

        return SUCCESS;
    }

    /**
     * Método que hace uso del Web Service para permite que un usuario vote
     * negativamente una publicación
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String votoNegativoPublicacion() throws Exception {
        Usuario usuario = (Usuario) loginLogout.session.get("user");
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        PublicacionWS publicacionCliente = new PublicacionWS();
        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {
        };
        GenericType<VotoPublicacion> tipoVotoPublicacion = new GenericType<VotoPublicacion>() {
        };
        GenericType<Boolean> tipoBoolean = new GenericType<Boolean>() {
        };
        Publicacion publicacion = publicacionCliente.find_XML(tipoPublicacion, idPublicacion);

        boolean encontrado = votoPublicacionCliente.existeVotoPublicacion(tipoBoolean, String.valueOf(usuario.getId()), idPublicacion);

        if (encontrado == false) {
            VotoPublicacion votoPublicacion = new VotoPublicacion(null, false, new Date());
            votoPublicacion.setIdPublicacion(publicacion);
            votoPublicacion.setIdUsuario(usuario);
            votoPublicacionCliente.create_XML(votoPublicacion);
        } else {
            VotoPublicacion votoPublicacion2 = votoPublicacionCliente.getVotoPublicacion_XML(tipoVotoPublicacion, String.valueOf(usuario.getId()), idPublicacion);
            if (votoPublicacion2.getTipo() == true) {
                votoPublicacion2.setTipo(false);
                votoPublicacionCliente.edit_XML(votoPublicacion2, String.valueOf(votoPublicacion2.getId()));
            }

        }

        return SUCCESS;
    }

    /**
     * Método que hace uso del Web Service para permite que un usuario deje de
     * seguir una publicación de la que fue subscrito
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String unFollowPublicacion() throws Exception {
        Usuario usuario = (Usuario) loginLogout.session.get("user");
        GenericType<Suscripcion> tipoSuscripcion = new GenericType<Suscripcion>() {
        };
        SuscripcionWS suscripcionCliente = new SuscripcionWS();
        Suscripcion suscripcion = suscripcionCliente.getSuscripcion_XML(tipoSuscripcion, String.valueOf(usuario.getId()), idPublicacion);

        suscripcionCliente.remove(String.valueOf(suscripcion.getId()));

        return SUCCESS;
    }

    /**
     * Método que hace uso del Web Service para permite que un usuario sea
     * seguidor de una publicación
     *
     * @return SUCCESS
     * @throws Exception
     */
    
    public String followPublicacion() throws Exception {
        Usuario usuario = (Usuario) loginLogout.session.get("user");
        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {
        };
        SuscripcionWS suscripcionCliente = new SuscripcionWS();
        PublicacionWS publicacionCliente = new PublicacionWS();

        Publicacion publiAux = publicacionCliente.find_JSON(tipoPublicacion, idPublicacion);

        Suscripcion suscripcion = new Suscripcion(null, new Date());
        suscripcion.setIdPublicacion(publiAux);
        suscripcion.setIdUsuario(usuario);

        suscripcionCliente.create_JSON(suscripcion);

        return SUCCESS;

    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getIdTema() {
        return idTema;
    }

    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }

}
