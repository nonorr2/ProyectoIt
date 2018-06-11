/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Usuario;
import WS.UsuarioWS;
import static acciones.loginLogout.session;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class MostrarUsuarios extends ActionSupport {

    Usuario usuario = new Usuario();
    private String idUsuarioEdit;
    private List<Usuario> usuarios;
    private String filtroUser;
    private String idUsuarioRemove;

    public MostrarUsuarios() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Metodo que hace uso del web service para obtener un usuario con el id
     * indicado.
     * @return
     * @throws Exception
     */
    public String editUser() throws Exception {
        GenericType<Usuario> tipoUser = new GenericType<Usuario>() {
        };
        UsuarioWS userWS = new UsuarioWS();
        usuario = userWS.find_XML(tipoUser, idUsuarioEdit);
        return SUCCESS;
    }

    public String perfil() throws Exception {
        Usuario usuarioSession = (Usuario) loginLogout.session.get("user");

        GenericType<Usuario> tipoUser = new GenericType<Usuario>() {
        };
        UsuarioWS usuarioWS = new UsuarioWS();
        this.usuario = usuarioWS.find_XML(tipoUser, String.valueOf(usuarioSession.getId()));
        return SUCCESS;
    }

    /**
     * Metodo que hace uso del web service para obtener una lista de los
     * usuarios qeu cumpla con la condición del filtro
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String filtrarUser() throws Exception {
        GenericType<List<Usuario>> tipoUsuarios = new GenericType<List<Usuario>>() {
        };
        UsuarioWS usuarioClient = new UsuarioWS();
        if (filtroUser.equals("")) {
            usuarios = usuarioClient.getUsuarioTipo_XML(tipoUsuarios);
        } else {
            usuarios = usuarioClient.getUsuariosPorNombre_JSON(tipoUsuarios, filtroUser);
        }

        return SUCCESS;
    }

    /**
     * Metodo para eliminar un suario de la BD
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String removeUser() throws Exception {
        UsuarioWS userWS = new UsuarioWS();
        userWS.remove(idUsuarioRemove);
        return SUCCESS;
    }

    /**
     * Método para eliminar la cuenta del usuario logueado
     *
     * @return
     * @throws Exception
     */
    public String removeMiCuenta() throws Exception {
        //loginLogout.session = (Map) ActionContext.getContext().get("session");
        loginLogout.session.clear();
        UsuarioWS userWS = new UsuarioWS();
        userWS.remove(idUsuarioRemove);
        return SUCCESS;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIdUsuarioEdit() {
        return idUsuarioEdit;
    }

    public void setIdUsuarioEdit(String idUsuarioEdit) {
        this.idUsuarioEdit = idUsuarioEdit;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getFiltroUser() {
        return filtroUser;
    }

    public void setFiltroUser(String filtroUser) {
        this.filtroUser = filtroUser;
    }

    public String getIdUsuarioRemove() {
        return idUsuarioRemove;
    }

    public void setIdUsuarioRemove(String idUsuarioRemove) {
        this.idUsuarioRemove = idUsuarioRemove;
    }

}
