/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Publicacion;
import WS.PublicacionWS;
import WS.Tematica;
import WS.TematicaWS;
import WS.Usuario;
import WS.VotoPublicacion;
import WS.VotoPublicacionWS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.ws.rs.core.GenericType;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author David
 */
public class GestionPublicacion extends ActionSupport {

    private String idPublicacionRemove;

    private String tituloPubliacion;
    private File fotoPubliacion;
    private String contenidoPubliacion;
    private String rutaPubliacion;
    private String tematicaPubliacion;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String idPublicacion;
    private String filtroPublicacion;

    private List<Publicacion> publicaciones;
    private String idTema;

    public GestionPublicacion() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String removePublicacion() throws Exception {
        PublicacionWS publicacionWS = new PublicacionWS();
        publicacionWS.remove(idPublicacionRemove);
        return SUCCESS;
    }

    /**
     * Método para crear una nueva publicación
     *
     * @return
     * @throws Exception
     */
    public String addPublicacion() throws Exception {
        ServletContext context = ServletActionContext.getServletContext();
//        String hola = context.getRealPath("/");
        String ruta = System.getProperty("catalina.home") + "/prueba/prueba2.png";
        File nuevo = new File(ruta);
        FileUtils.copyFile(fotoPubliacion, nuevo);
        nuevo.renameTo(new File("/images/publicaciones/" + "prueba1subida.png"));
        PublicacionWS publicacionWS = new PublicacionWS();
        TematicaWS tematicaWS = new TematicaWS();

        //loginLogout.session = (Map) ActionContext.getContext().get("session");
        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<Tematica> tipoTematica = new GenericType<Tematica>() {
        };
        Tematica tematica = tematicaWS.find_XML(tipoTematica, tematicaPubliacion);

        Publicacion publicacion = new Publicacion(null, tituloPubliacion, contenidoPubliacion, new Date(), new Date(), rutaPubliacion, ruta);
        publicacion.setIdUsuario(usuario);
        publicacion.setIdTematica(tematica);

        publicacionWS.create_JSON(publicacion);
        return SUCCESS;
    }

//    @Override
//    public void validate() {
//        if(this.getTituloPubliacion().trim().length() == 0 || this.getTituloPubliacion() == null){
//            addFieldError("tituloPubliacion", "El título de la publicación es obligatorio");
//        }
//    }
    public String filtroPubli() throws Exception {
        GenericType<List<Publicacion>> tipoUsuarios = new GenericType<List<Publicacion>>() {
        };
        PublicacionWS usuarioClient = new PublicacionWS();
        if (filtroPublicacion.equals("")) {
            publicaciones = usuarioClient.findAll_JSON(tipoUsuarios);
        } else {
            publicaciones = usuarioClient.getPublicacionPorTitulo_JSON(tipoUsuarios, filtroPublicacion);
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

    public String getIdTema() {
        return idTema;
    }

    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }

    public String getIdPublicacionRemove() {
        return idPublicacionRemove;
    }

    public void setIdPublicacionRemove(String idPublicacionRemove) {
        this.idPublicacionRemove = idPublicacionRemove;
    }

    public String getTituloPubliacion() {
        return tituloPubliacion;
    }

    public void setTituloPubliacion(String tituloPubliacion) {
        this.tituloPubliacion = tituloPubliacion;
    }

    public File getFotoPubliacion() {
        return fotoPubliacion;
    }

    public void setFotoPubliacion(File fotoPubliacion) {
        this.fotoPubliacion = fotoPubliacion;
    }

    public String getContenidoPubliacion() {
        return contenidoPubliacion;
    }

    public void setContenidoPubliacion(String contenidoPubliacion) {
        this.contenidoPubliacion = contenidoPubliacion;
    }

    public String getRutaPubliacion() {
        return rutaPubliacion;
    }

    public void setRutaPubliacion(String rutaPubliacion) {
        this.rutaPubliacion = rutaPubliacion;
    }

    public String getTematicaPubliacion() {
        return tematicaPubliacion;
    }

    public void setTematicaPubliacion(String tematicaPubliacion) {
        this.tematicaPubliacion = tematicaPubliacion;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
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

}
