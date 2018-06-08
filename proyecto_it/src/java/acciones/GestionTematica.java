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

    private String id;
    private String imagen;
    private String nombre;
    TematicaWS tematicasWS = new TematicaWS();
    Boolean error = false;
    Boolean tematicaIguales = false;

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
        GenericType<Boolean> tipoTematica = new GenericType<Boolean>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        String ruta = "images/tematicas/" + imagen;
        Tematica newTematica = new Tematica(nombre, ruta);
        if (tematicaClient.encontrarTematica(tipoTematica, newTematica.getNombre())) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
    
    
    
    public void validate(){
        if (imagen == null || imagen.length() == 0) {
            addFieldError("imagen", "La imagen es obligatoria");
            error = true;
        }
        
        if(nombre.trim().length() == 0){
            addFieldError("nombre", "El campo nombre es obligatorio");
            error = true;
        }
        
        if(nombre.trim().length() > 149){
            addFieldError("nombre", "El nombre no puede ser mayor a 150 palabras");
            error = true;
        }
    }

}
