/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import WS.Usuario;
import WS.UsuarioWS;
import static acciones.loginLogout.session;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author David
 */
public class GestionUsuario extends ActionSupport {

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
    private String imgPerfilUsuario;
    private String confirmarPassword;
    private String myFile;
    

    public GestionUsuario() {

    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String editUserPersistencia() throws Exception {
        String ruta = "images/fotosPerfil/" + myFile;
        UsuarioWS userWS = new UsuarioWS();
        Usuario user = new Usuario(Integer.valueOf(id), nombre, apellidos, nickname, password, email, ruta, fechaNacimiento);
        userWS.edit_JSON(user, id);
        return SUCCESS;
    }

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

    public String addUsuario() throws Exception {
        UsuarioWS userWS = new UsuarioWS();
        GenericType<Usuario> tipoUsuario = new GenericType<Usuario>() {
        };

        if (this.password.equals(this.confirmarPassword)) {
            Usuario newUsuario = new Usuario(null, nombre, apellidos, nickname, password, email, false, fechaNacimiento, foto);
            userWS.create_XML(newUsuario);

            Usuario usu = userWS.getUsuarioByUsername_XML(tipoUsuario, nickname);
            session = (Map) ActionContext.getContext().get("session");
            session.put("user", usu);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    @RequiredStringValidator(message = "El nombre es obligatorio")
    @StringLengthFieldValidator(maxLength = "50", message = "Tamaño máximo 50 carácteres")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    @RequiredStringValidator(message = "El apellido es obligatorio")
    @StringLengthFieldValidator(maxLength = "50", message = "Tamaño máximo 50 carácteres")
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNickname() {
        return nickname;
    }

    @RequiredStringValidator(message = "El nombre de usuario es obligatorio")
    @StringLengthFieldValidator(maxLength = "50", message = "Tamaño máximo 50 carácteres")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    @RequiredStringValidator(message = "El email es obligatorio")
    @EmailValidator(message = "Formato del email incorrecto")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(message = "La contraseña es obligatoria")
    @StringLengthFieldValidator(minLength = "5", maxLength = "10", message = "La contraseña debe estar comprendida entren 5 y 10 carácteres")
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    @RequiredFieldValidator(message = "La fecha de nacimiento es obligatoria")
    @DateRangeFieldValidator(message = "Formato de la fecha de nacimiento incorrecto")
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

    public String getImgPerfilUsuario() {
        return imgPerfilUsuario;
    }

    public void setImgPerfilUsuario(String imgPerfilUsuario) {
        this.imgPerfilUsuario = imgPerfilUsuario;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    public String getMyFile() {
        return myFile;
    }

    public void setMyFile(String myFile) {
        this.myFile = myFile;
    } 
    
}
