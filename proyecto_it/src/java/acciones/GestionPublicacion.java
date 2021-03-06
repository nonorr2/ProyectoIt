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
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.core.GenericType;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class GestionPublicacion extends ActionSupport {

    //private String idPublicacionRemove;
    private String tituloPubliacion;
    private File fotoPubliacion;
    private String contenidoPubliacion;
    private String rutaPubliacion;
    private String tematicaPubliacion;
    private String idPublicacion;
    private String filtroPublicacion;

    private List<Publicacion> publicaciones;
    private List<Tematica> tematicas;

    Boolean error = false;

    public GestionPublicacion() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método para crear una nueva publicación
     *
     * @return
     * @throws Exception
     */
    public String addPublicacion() throws Exception {
        String rutaRelativa = null;

        if (fotoPubliacion != null) {
            ServletContext context = ServletActionContext.getServletContext();
            String nombrePublicacion = tituloPubliacion.replace(" ", "_");
            String nombreFichero = nombrePublicacion + ".png";
            rutaRelativa = "images/publicaciones/" + nombreFichero;
            String ruta = context.getRealPath("/") + rutaRelativa;
            File nuevo = new File(ruta);
            FileUtils.copyFile(fotoPubliacion, nuevo);
        }

        PublicacionWS publicacionWS = new PublicacionWS();
        TematicaWS tematicaWS = new TematicaWS();

        Usuario usuario = (Usuario) loginLogout.session.get("user");

        GenericType<Tematica> tipoTematica = new GenericType<Tematica>() {
        };
        Tematica tematica = tematicaWS.find_XML(tipoTematica, tematicaPubliacion);

        Publicacion publicacion = new Publicacion(null, tituloPubliacion, contenidoPubliacion, new Date(), new Date(), rutaPubliacion, rutaRelativa);
        publicacion.setIdUsuario(usuario);
        publicacion.setIdTematica(tematica);

        publicacionWS.create_XML(publicacion);

        return SUCCESS;
    }

    /**
     * Método para validar los campos del formulario de nueva publicación.
     */
    @Override
    public void validate() {
        //Listar las temáticas para el selec de añadir piblicacion
        GenericType<List<Tematica>> tipoListTematica = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.findAll_XML(tipoListTematica);

        if (this.getTituloPubliacion() == null || this.getTituloPubliacion().trim().length() == 0) {
            addFieldError("tituloPubliacion", getText("tituloPubliacion.requerido"));
            error = true;
        } else if (this.getTituloPubliacion().length() > 500) {
            addFieldError("tituloPubliacion", getText("tituloPubliacion.tamano"));
            error = true;
        }

        if (this.getContenidoPubliacion() == null || this.getContenidoPubliacion().trim().length() == 0) {
            addFieldError("contenidoPubliacion", getText("contenidoPubliacion.requerido"));
            error = true;
        } else if (this.getContenidoPubliacion().length() > 5000) {
            addFieldError("contenidoPubliacion", getText("contenidoPubliacion.tamano"));
            error = true;
        }

        if (!this.getRutaPubliacion().matches("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            addFieldError("rutaPubliacion", getText("rutaPubliacion.formato"));
            error = true;
        }
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

    public List<Tematica> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<Tematica> tematicas) {
        this.tematicas = tematicas;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

}
