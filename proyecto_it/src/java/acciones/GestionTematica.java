/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Tematica;
import WS.TematicaWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class GestionTematica extends ActionSupport {

    private String idTematicaRemove;
    private String idTematicaEdit;
    Tematica tematica = new Tematica();
    private String id;
    private String imagen;
    private String nombre;
    private String filtrarTema;

    List<Tematica> tematicas;

    TematicaWS tematicasWS = new TematicaWS();

    public GestionTematica() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String editTematica() throws Exception {
        GenericType<Tematica> tipoTema = new GenericType<Tematica>() {
        };
        tematica = tematicasWS.find_JSON(tipoTema, idTematicaEdit);
        return SUCCESS;
    }

    public String editTemaPersistencia() throws Exception {
        String ruta = "images/tematicas/" + imagen;
        Tematica newTematica = new Tematica(Integer.valueOf(id), nombre, ruta);
        tematicasWS.edit_JSON(newTematica, id);
        return SUCCESS;
    }

    public String filtrarTema() throws Exception {
        GenericType<List<Tematica>> tipoUsuarios = new GenericType<List<Tematica>>() {
        };
        TematicaWS usuarioClient = new TematicaWS();
        if (filtrarTema.equals("")) {
            tematicas = usuarioClient.findAll_XML(tipoUsuarios);
        } else {
            tematicas = usuarioClient.getTematicasPorNombre_XML(tipoUsuarios, filtrarTema);
        }

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

    public String getIdTematicaEdit() {
        return idTematicaEdit;
    }

    public void setIdTematicaEdit(String idTematicaEdit) {
        this.idTematicaEdit = idTematicaEdit;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
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

    public String getFiltrarTema() {
        return filtrarTema;
    }

    public void setFiltrarTema(String filtrarTema) {
        this.filtrarTema = filtrarTema;
    }

    public List<Tematica> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<Tematica> tematicas) {
        this.tematicas = tematicas;
    }
    
    

}
