/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Tematica;
import WS.TematicaWS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import javax.servlet.ServletContext;
import javax.ws.rs.core.GenericType;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author David
 */
public class GestionTematica extends ActionSupport {

    private String id;
    private String nombre;
    TematicaWS tematicasWS = new TematicaWS();
    Boolean error = false;
    Boolean tematicaIguales = false;
    private File imgTematica;
    Tematica tematica = new Tematica();

    public GestionTematica() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Metodo que hace uso del web service para guardar las modificaciones de la
     * tematica en la BD
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String editTemaPersistencia() throws Exception {
        String rutaRelativa = null;

        if (imgTematica != null) {
            ServletContext context = ServletActionContext.getServletContext();
            String nombreFichero = nombre + ".png";
            rutaRelativa = "images/tematicas/" + nombreFichero;
            String ruta = context.getRealPath("/") + rutaRelativa;
            File nuevo = new File(ruta);
            FileUtils.copyFile(imgTematica, nuevo);
        } else {
            GenericType<Tematica> tipoTematica = new GenericType<Tematica>() {
            };
            tematica = tematicasWS.find_XML(tipoTematica, id);
            rutaRelativa = tematica.getImagen();
        }
        Tematica newTematica = new Tematica(Integer.valueOf(id), nombre, rutaRelativa);
        tematicasWS.edit_JSON(newTematica, id);
        return SUCCESS;
    }

    /**
     * Metodo que hace uso del web service para guardar la nueva tematica en la
     * BD
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String addTemaPersistencia() throws Exception {
        GenericType<Boolean> tipoTematica = new GenericType<Boolean>() {
        };

        String rutaRelativa = null;

        if (imgTematica != null) {
            ServletContext context = ServletActionContext.getServletContext();
            String nombreFichero = nombre + ".png";
            rutaRelativa = "images/tematicas/" + nombreFichero;
            String ruta = context.getRealPath("/") + rutaRelativa;
            File nuevo = new File(ruta);
            FileUtils.copyFile(imgTematica, nuevo);
        }

        Tematica newTematica = new Tematica(nombre, rutaRelativa);
        if (tematicasWS.encontrarTematica(tipoTematica, newTematica.getNombre())) {
            tematicaIguales = true;
            return ERROR;
        } else {
            tematicasWS.create_JSON(newTematica);
            return SUCCESS;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getImgTematica() {
        return imgTematica;
    }

    public void setImgTematica(File imgTematica) {
        this.imgTematica = imgTematica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getTematicaIguales() {
        return tematicaIguales;
    }

    public void setTematicaIguales(Boolean tematicaIguales) {
        this.tematicaIguales = tematicaIguales;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

    /**
     * Metodo para validar los campos del formulario de nueva tematica y editar
     * tematica del administrador
     */
    public void validate() {
        GenericType<Tematica> tipoTema = new GenericType<Tematica>() {
        };

        if (id != null) {
            tematica = tematicasWS.find_XML(tipoTema, id);
            if ((imgTematica == null || imgTematica.length() == 0) && tematica.getImagen() == null) {
                addFieldError("imgTematica", "La imagen es obligatoria");
                error = true;
            }
        } else if (imgTematica == null || imgTematica.length() == 0) {
            addFieldError("imgTematica", "La imagen es obligatoria");
            error = true;
        }

        if (nombre.trim().length() == 0) {
            addFieldError("nombre", "El campo titulo es obligatorio");
            error = true;
        } else if (nombre.trim().length() > 149) {
            addFieldError("nombre", "El titulo no puede ser mayor a 150 palabras");
            error = true;
        }
    }

}
