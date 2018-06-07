/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Publicacion;
import WS.PublicacionWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Lydia
 */
public class MostrarPublicaciones extends ActionSupport {
    
    private String filtroPublicacion;
    private List<Publicacion> publicaciones;
    private String idTema;
    
    public MostrarPublicaciones() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Métedo para filtrar las publicaciones por el título.
     * @return 
     * @throws Exception 
     */
    public String filtroPubli() throws Exception {
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        PublicacionWS usuarioClient = new PublicacionWS();
        if (filtroPublicacion.equals("")) {
            publicaciones = usuarioClient.findAll_JSON(tipoPublicacion);
        } else {
            publicaciones = usuarioClient.getPublicacionPorTitulo_JSON(tipoPublicacion, filtroPublicacion);
        }

        return SUCCESS;
    }
    
    public String publicacionesByTema() throws Exception{
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        PublicacionWS publicacionClient = new PublicacionWS();
        publicaciones = publicacionClient.getPublicacionesByTema_JSON(tipoPublicacion, idTema);
        return SUCCESS;
    }

    public String getFiltroPublicacion() {
        return filtroPublicacion;
    }

    public void setFiltroPublicacion(String filtroPublicacion) {
        this.filtroPublicacion = filtroPublicacion;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getIdTema() {
        return idTema;
    }

    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }
    
    
    
}
