/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Publicacion;
import WS.PublicacionWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
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
    
    public String execute() throws Exception {        
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {};
        this.misPublicaciones = (List<Publicacion>) this.publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, "2");
        return SUCCESS;
    }
    
    public String borrarPublicacion() throws Exception{
        this.publicacionCliente.remove(idPublicacion);
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {};
        this.misPublicaciones = (List<Publicacion>) this.publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, "2");
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
