/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.PublicacionWS;
import WS.Tematica;
import WS.TematicaDecorado;
import WS.TematicaWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
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
    List<TematicaDecorado> tematicas;
    private String idTematicaRemove;

    public MostrarTematicas() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método que hace uso del web service para obtener una temética con el id
     * especificado
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String editTematica() throws Exception {
        GenericType<Tematica> tipoTema = new GenericType<Tematica>() {
        };
        tematica = tematicasWS.find_JSON(tipoTema, idTematicaEdit);
        return SUCCESS;
    }

    /**
     * Método que hace uso del web service para obtener una lista de las
     * teméticas qeu cumpla con la condición del filtro
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String filtrarTema() throws Exception {
        GenericType<List<Tematica>> tipoTematicaaAdmin = new GenericType<List<Tematica>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {
        };

        TematicaWS tematicaClient = new TematicaWS();
        PublicacionWS publicacionClient = new PublicacionWS();
        List<Tematica> listTematica;

        if (filtrarTema.equals("")) {
            listTematica = tematicaClient.findAll_XML(tipoTematicaaAdmin);
        } else {
            listTematica = tematicaClient.getTematicasPorNombre_XML(tipoTematicaaAdmin, filtrarTema);
        }

        this.tematicas = new ArrayList<TematicaDecorado>();

        for (Tematica t : listTematica) {
            TematicaDecorado tematicaDecorado = new TematicaDecorado();
            Long numPubliTema = publicacionClient.getNumPublicacionesByTematica(tipoLong, String.valueOf(t.getId()));
            tematicaDecorado.setTematicas(t);
            tematicaDecorado.setNumPublicacionesDeUnaTematica(numPubliTema.intValue());
            this.tematicas.add(tematicaDecorado);
        }
        return SUCCESS;
    }

    /**
     * Metodo para eliminar una tematica de la BD
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String removeTematica() throws Exception {
        tematicasWS.remove(idTematicaRemove);
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

    public List<TematicaDecorado> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<TematicaDecorado> tematicas) {
        this.tematicas = tematicas;
    }

    public String getIdTematicaRemove() {
        return idTematicaRemove;
    }

    public void setIdTematicaRemove(String idTematicaRemove) {
        this.idTematicaRemove = idTematicaRemove;
    }

}
