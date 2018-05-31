/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Usuario;
import WS.UsuarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class GestionUsuario extends ActionSupport {

    private String idUsuarioEdit;
    private String idUsuarioRemove;
    Usuario usuario = new Usuario();
    private String id;
    private String nombre;
    private String apellidos;
    private String nickname;
    private String email;
    private String password;
    private Date fechaNacimiento;
    private String foto;

    public GestionUsuario() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String editUser() throws Exception {
        GenericType<Usuario> tipoUser = new GenericType<Usuario>() {
        };
        UsuarioWS userWS = new UsuarioWS();
        usuario = userWS.find_XML(tipoUser, idUsuarioEdit);
        return SUCCESS;
    }

    public String editUserPersistencia() throws Exception {
        UsuarioWS userWS = new UsuarioWS();
        Usuario user = new Usuario(Integer.valueOf(id), nombre, apellidos, nickname, password, fechaNacimiento, "prueba", email);
        userWS.edit_JSON(user, id);
        return SUCCESS;
    }

    public String removeUser() throws Exception {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdUsuarioRemove() {
        return idUsuarioRemove;
    }

    public void setIdUsuarioRemove(String idUsuarioRemove) {
        this.idUsuarioRemove = idUsuarioRemove;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    

}
