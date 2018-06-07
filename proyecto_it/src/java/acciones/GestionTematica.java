/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Tematica;
import WS.TematicaWS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class GestionTematica extends ActionSupport {

    private String idTematicaRemove;
    private String id;
    private String imagen;
    private String nombre;
    TematicaWS tematicasWS = new TematicaWS();

    public GestionTematica() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String editTemaPersistencia() throws Exception {
        String ruta = "images/tematicas/" + imagen;
        Tematica newTematica = new Tematica(Integer.valueOf(id), nombre, ruta);
        tematicasWS.edit_JSON(newTematica, id);
        return SUCCESS;
    }

    public String addTemaPersistencia() throws Exception {
//        String ruta = "images/tematicas/" +imagen;
        Tematica newTematica = new Tematica(nombre, imagen);
        tematicasWS.create_JSON(newTematica);
        return SUCCESS;
    }

    public String removeTematica() throws Exception {
        tematicasWS.remove(idTematicaRemove);
        return SUCCESS;
    }

    public String getIdTematicaRemove() {
        return idTematicaRemove;
    }

    public void setIdTematicaRemove(String idTematicaRemove) {
        this.idTematicaRemove = idTematicaRemove;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    @RequiredStringValidator(message = "El titulo es obligatorio")
    @StringLengthFieldValidator(maxLength = "50", message = "Tamaño máximo 50 carácteres")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
