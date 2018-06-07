/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Tematica;
import WS.TematicaWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class MostrarTematicas extends ActionSupport {

    Tematica tematica = new Tematica();
    TematicaWS tematicasWS = new TematicaWS();
    private String idTematicaEdit;
    private String filtrarTema;
    List<Tematica> tematicas;
    
    public MostrarTematicas() {
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

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

    public TematicaWS getTematicasWS() {
        return tematicasWS;
    }

    public void setTematicasWS(TematicaWS tematicasWS) {
        this.tematicasWS = tematicasWS;
    }

    public String getIdTematicaEdit() {
        return idTematicaEdit;
    }

    public void setIdTematicaEdit(String idTematicaEdit) {
        this.idTematicaEdit = idTematicaEdit;
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
