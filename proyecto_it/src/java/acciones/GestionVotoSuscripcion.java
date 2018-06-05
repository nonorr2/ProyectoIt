/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Publicacion;
import WS.PublicacionWS;
import WS.Suscripcion;
import WS.SuscripcionWS;
import WS.Usuario;
import WS.VotoPublicacion;
import WS.VotoPublicacionWS;
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
    
    public GestionVotoSuscripcion() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
//    public String votoPositivoPublicacion() throws Exception {
//        loginLogout.session = (Map) ActionContext.getContext().get("session");
//        Usuario usuario = (Usuario) loginLogout.session.get("user");
//        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
//        PublicacionWS publicacionCliente = new PublicacionWS();
//        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {
//        };
//        Publicacion publicacion = publicacionCliente.find_XML(tipoPublicacion, this.idPublicacion);
//        VotoPublicacion votoPositivo = new VotoPublicacion();
//        votoPositivo.setPublicacion(publicacion);
//        votoPositivo.setUsuario(usuario);
//        votoPositivo.setTipo(true);
//        votoPositivo.setFechaHora(new Date());
//        votoPublicacionCliente.create_JSON(votoPositivo);
//        return SUCCESS;
//    }
    
//    public String suscribirPublicacion() throws Exception{
//        loginLogout.session = (Map) ActionContext.getContext().get("session");
//        Usuario usuario = (Usuario) loginLogout.session.get("user");
//        PublicacionWS publicacionCliente = new PublicacionWS();
//        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {};
//        Publicacion publicacion = publicacionCliente.find_XML(tipoPublicacion, this.idPublicacion);
//        
//        SuscripcionWS suscripcionClinete = new SuscripcionWS();
//        Suscripcion newSuscripcion = new Suscripcion();
//        newSuscripcion.setFechaHora(new Date());
//        newSuscripcion.setUsuario(usuario);
//        newSuscripcion.setPublicacion(publicacion);
//        
//        suscripcionClinete.create_XML(newSuscripcion);
//        return SUCCESS;
//    }
    
//    public String unFollowPublicacion() throws Exception{
//        loginLogout.session = (Map) ActionContext.getContext().get("session");
//        Usuario usuario = (Usuario) loginLogout.session.get("user");
//        PublicacionWS publicacionCliente = new PublicacionWS();
//        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {};
//        Publicacion publicacion = publicacionCliente.find_XML(tipoPublicacion, this.idPublicacion);
//        
//        SuscripcionWS suscripcionClinete = new SuscripcionWS();
//        Suscripcion newSuscripcion = new Suscripcion();
//        newSuscripcion.setFechaHora(new Date());
//        newSuscripcion.setUsuario(usuario);
//        newSuscripcion.setPublicacion(publicacion);
//        SuscripcionPK susPK = new SuscripcionPK(usuario.getId(), Integer.parseInt(idPublicacion));
//        
//        suscripcionClinete.remove(String.valueOf(susPK));
//        return SUCCESS;
//    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
    
    
}
