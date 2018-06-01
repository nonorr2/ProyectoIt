/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.PublicacionWS;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author David
 */
public class GestionPublicacion extends ActionSupport {
    
    private String idPublicacionRemove;
    
    public GestionPublicacion() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String removePublicacion() throws Exception{
        PublicacionWS publicacionWS = new PublicacionWS();
        publicacionWS.remove(idPublicacionRemove);
        return SUCCESS;
    }

    public String getIdPublicacionRemove() {
        return idPublicacionRemove;
    }

    public void setIdPublicacionRemove(String idPublicacionRemove) {
        this.idPublicacionRemove = idPublicacionRemove;
    }
    
    
    
}
