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
import java.text.SimpleDateFormat;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.apache.commons.io.FileUtils;

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
    private String imgPerfilUsuario;
    private String confirmarPassword;

    private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private String destPath;

    public GestionUsuario() {

    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String perfil() throws Exception {
        loginLogout.session = (Map) ActionContext.getContext().get("session");
        Usuario usuarioSession = (Usuario) loginLogout.session.get("user");

        GenericType<Usuario> tipoUser = new GenericType<Usuario>() {
        };
        UsuarioWS usuarioWS = new UsuarioWS();
        this.usuario = usuarioWS.find_XML(tipoUser, String.valueOf(usuarioSession.getId()));
        return SUCCESS;
    }

    public String editUser() throws Exception {
        GenericType<Usuario> tipoUser = new GenericType<Usuario>() {
        };
        UsuarioWS userWS = new UsuarioWS();
        usuario = userWS.find_XML(tipoUser, idUsuarioEdit);
        return SUCCESS;
    }

    public String editUserPersistencia() throws Exception {
        destPath = "images/fotosPerfil/";
        String ruta = "images/fotosPerfil/" + myFileFileName;

        File desFile = new File(destPath, myFileFileName);
        FileUtils.copyFile(myFile, desFile);

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
        loginLogout.session = (Map) ActionContext.getContext().get("session");
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

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

}
