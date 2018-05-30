/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Publicacion;
import WS.Tematica;
import WS.Usuario;
import WS.UsuarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class menu_top_admin extends ActionSupport {
      
    private List<Usuario> usuarios;
    private List<Tematica> tematicas;
    private List<Publicacion> publicaciones;
    
    public menu_top_admin() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String usuarios() throws Exception {
        GenericType<List<Usuario>> tipoUsuarios = new GenericType<List<Usuario>>(){
        };
        UsuarioWS usuarioClient = new UsuarioWS();
        usuarios = usuarioClient.findAll_JSON(tipoUsuarios);
        return SUCCESS;
    }
    
    
}
