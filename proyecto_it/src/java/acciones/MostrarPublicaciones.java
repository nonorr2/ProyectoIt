/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionDecorado;
import WS.PublicacionWS;
import WS.Tematica;
import WS.TematicaWS;
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
    private List<Publicacion> publicaciones;
    private String idTema;
    private List<PublicacionDecorado> listaPublicaciones;
    private List<Tematica> tematicas;

    public MostrarPublicaciones() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Métedo para filtrar las publicaciones por el título.
     *
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

    public String filtroMisPublicaciones() throws Exception {
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {
        };

        PublicacionWS publicacionCliente = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        this.listaPublicaciones = new ArrayList<PublicacionDecorado>();
        List<Publicacion> misPublicaciones;
        
        if (filtroPublicacion.equals("")) {
            misPublicaciones = (List<Publicacion>) publicacionCliente.getMisPublicaciones_XML(tipoPublicacion, String.valueOf(usuario.getId()));
            
        } else {
            misPublicaciones = (List<Publicacion>) publicacionCliente.getMisPublicacionesPorTitulo_JSON(tipoPublicacion, filtroPublicacion, String.valueOf(usuario.getId()));
        }
        
        for(Publicacion publicacion : misPublicaciones){
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
        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {};
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.getTematicasMasPopulares_JSON(tipoTematica);
        
        return SUCCESS;
    }
    
    public String filtroPublicacionesSuscrito() throws Exception {
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {
        };

        PublicacionWS publicacionCliente = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        this.listaPublicaciones = new ArrayList<PublicacionDecorado>();
        List<Publicacion> publicacionesSuscrito;
        
        if (filtroPublicacion.equals("")) {
            publicacionesSuscrito = (List<Publicacion>) publicacionCliente.getPublicacionesSuscritoOrdenadoNumComentarios_XML(tipoPublicacion, String.valueOf(usuario.getId()));
            
        } else {
            publicacionesSuscrito = (List<Publicacion>) publicacionCliente.getPublicacionesSuscritoPorTitulo_XML(tipoPublicacion, String.valueOf(usuario.getId()), filtroPublicacion);
        }
        
        for(Publicacion publicacion : publicacionesSuscrito){
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
        
        return SUCCESS;
    }

    public String publicacionesByTema() throws Exception {
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

    public List<PublicacionDecorado> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<PublicacionDecorado> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public List<Tematica> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<Tematica> tematicas) {
        this.tematicas = tematicas;
    }

    
}
