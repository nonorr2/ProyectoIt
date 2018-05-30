/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Usuario;
import WS.UsuarioWS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scope;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Nono
 */
public class loginLogout extends ActionSupport {

    String usuario;
    String password;
    static Map session;

    public loginLogout() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String login() throws Exception {
        GenericType<Boolean> tipoPassword = new GenericType<Boolean>() {
        };
        GenericType<Usuario> tipoUsuario = new GenericType<Usuario>() {
        };
        UsuarioWS usuarioClient = new UsuarioWS();

        Boolean resultado = usuarioClient.login(tipoPassword, usuario, password);
        if (resultado) {
            Usuario usu = usuarioClient.getUsuarioByUsername_XML(tipoUsuario, usuario);
            session = (Map) ActionContext.getContext().get("session");
            session.put("user", usu);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
