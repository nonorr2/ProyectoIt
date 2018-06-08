/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionDecorado;
import WS.PublicacionDecoradorExterno;
import WS.PublicacionWS;
import WS.Suscripcion;
import WS.SuscripcionWS;
import WS.Usuario;
import WS.VotoPublicacionWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Lydia
 */
public class MostrarPublicaciones extends ActionSupport {
    
    private String filtroPublicacion;
    private List<PublicacionDecorado> publicaciones;
    private String idTema;
    private List<PublicacionDecoradorExterno> publicacionesDecoradas;
    
    public MostrarPublicaciones() {
        publicacionesDecoradas = new ArrayList<>();
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
        GenericType<List<Publicacion>> tipoPublicacionAdmin = new GenericType<List<Publicacion>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {};

        PublicacionWS publicacionClient = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        List<Publicacion> listPublicaciones;
        
        if (filtroPublicacion.equals("")) {
            listPublicaciones = publicacionClient.findAll_XML(tipoPublicacionAdmin);
        }else{
            listPublicaciones = publicacionClient.getPublicacionPorTitulo_XML(tipoPublicacionAdmin, filtroPublicacion);
        }
         
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
    
    public String publicacionesByTema() throws Exception {
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        PublicacionWS publicacionClient = new PublicacionWS();
        List<Publicacion> listadoPublicaciones = publicacionClient.getPublicacionesByTema_JSON(tipoPublicacion, idTema);

        VotoPublicacionWS votoPublicacionClient = new VotoPublicacionWS();
        SuscripcionWS suscripcionClient = new SuscripcionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();

        GenericType<Suscripcion> tipoSuscripcion = new GenericType<Suscripcion>() {
        };
        GenericType<Long> tipoVotoPublicacion = new GenericType<Long>() {
        };

        Usuario usu = (Usuario) loginLogout.session.get("user");

        for (Publicacion publicacion : listadoPublicaciones) {
            PublicacionDecoradorExterno aux = new PublicacionDecoradorExterno();
            Suscripcion auxSuscripcion = suscripcionClient.getSuscripcion_JSON(tipoSuscripcion, String.valueOf(usu.getId()), String.valueOf(publicacion.getId()));

            aux.setPublicacion(publicacion);
            aux.setNumVotosNegativosPublicacion(votoPublicacionClient.getVotosNegativos(tipoVotoPublicacion, String.valueOf(publicacion.getId())).intValue());
            aux.setNumVotosPositivosPublicacion(votoPublicacionClient.getVotosPositivos(tipoVotoPublicacion, String.valueOf(publicacion.getId())).intValue());
            aux.setNumComentarios(comentarioCliente.getNumComentarios(tipoVotoPublicacion, String.valueOf(publicacion.getId())).intValue());

            if (auxSuscripcion != null) {
                aux.setSuscripcion(true);
            } else {
                aux.setSuscripcion(false);
            }

            publicacionesDecoradas.add(aux);
        }
        return SUCCESS;
    }

    public String getFiltroPublicacion() {
        return filtroPublicacion;
    }

    public void setFiltroPublicacion(String filtroPublicacion) {
        this.filtroPublicacion = filtroPublicacion;
    }

    public List<PublicacionDecorado> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionDecorado> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getIdTema() {
        return idTema;
    }

    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }

    public List<PublicacionDecoradorExterno> getPublicacionesDecoradas() {
        return publicacionesDecoradas;
    }

    public void setPublicacionesDecoradas(List<PublicacionDecoradorExterno> publicacionesDecoradas) {
        this.publicacionesDecoradas = publicacionesDecoradas;
    }
    
    
    
}
