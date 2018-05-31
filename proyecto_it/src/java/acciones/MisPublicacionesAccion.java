/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Publicacion;
import WS.PublicacionWS;
import WS.Usuario;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Lydia
 */
public class MisPublicacionesAccion extends ActionSupport {
    
    private PublicacionWS publicacionCliente = new PublicacionWS();
    private List<Publicacion> misPublicaciones;      
    private String idPublicacion;
    
    public MisPublicacionesAccion() {
    }
    
    /**
     * Método para lista todas las publiaciones que ha creado el usuario logueado.
     * @return 
     * @throws Exception 
     */
    public String execute() throws Exception {      
        Map session = (Map) ActionContext.getContext().get("session");
        Usuario usuario = (Usuario) session.get("user");
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {};
        this.misPublicaciones = (List<Publicacion>) this.publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, String.valueOf(usuario.getId()));
        return SUCCESS;
    }
    
    /**
     * Método para borrar borrar una publicación creada por el usuario logueado.
     * @return
     * @throws Exception 
     */
    public String borrarPublicacion() throws Exception{
        this.publicacionCliente.remove(idPublicacion);
        
        Map session = (Map) ActionContext.getContext().get("session");
        Usuario usuario = (Usuario) session.get("user");
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {};
        this.misPublicaciones = (List<Publicacion>) this.publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, String.valueOf(usuario.getId()));
        return SUCCESS;
    }

    public PublicacionWS getPublicacionCliente() {
        return publicacionCliente;
    }

    public void setPublicacionCliente(PublicacionWS publicacionCliente) {
        this.publicacionCliente = publicacionCliente;
    }

    public List<Publicacion> getMisPublicaciones() {
        return misPublicaciones;
    }

    public void setMisPublicaciones(List<Publicacion> misPublicaciones) {
        this.misPublicaciones = misPublicaciones;
    }
    
    
    
}
