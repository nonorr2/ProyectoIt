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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Nono
 */
public class loginLogout extends ActionSupport implements ServletResponseAware, ServletRequestAware {

    String usuario;
    String password;
    static Map session;

    protected HttpServletResponse servletResponse;
    protected HttpServletRequest servletRequest;

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

            Cookie cookie = new Cookie("user", String.valueOf(usu.getId()));
            cookie.setMaxAge(604800);
            servletResponse.addCookie(cookie);

            if (usu.getTipo()) {
                return SUCCESS;
            } else {
                return LOGIN;
            }
        } else {
            return ERROR;
        }
    }

    public String logout() throws Exception {
        loginLogout.session.clear();
        Cookie cookie[] = servletRequest.getCookies();
        return SUCCESS;
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

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

}
