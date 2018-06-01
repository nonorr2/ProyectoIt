/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Tematica;
import WS.TematicaWS;
import com.opensymphony.xwork2.ActionSupport;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class GestionTematica extends ActionSupport {
    
    private String idTematicaRemove;
    private String idTematicaEdit;
    Tematica tematica = new Tematica();
    
    public GestionTematica() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String editTematica() throws Exception {
        GenericType<Tematica> tipoTema = new GenericType<Tematica>(){};
        TematicaWS tematicasWS = new TematicaWS();
        tematica = tematicasWS.find_JSON(tipoTema, idTematicaEdit);
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
    
    
}
