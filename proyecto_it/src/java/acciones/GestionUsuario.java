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
import java.io.File;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.ws.rs.core.GenericType;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author David
 */
public class GestionUsuario extends ActionSupport {

    Usuario usuario = new Usuario();
    private String id;
    private String nombre;
    private String apellidos;
    private String nickname;
    private String email;
    private String password;
    private Date fechaNacimiento;
    private File imgPerfilUsuario;
    private String editUsuario;
    UsuarioWS userWS = new UsuarioWS();

    public GestionUsuario() {

    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String editUserPersistencia() throws Exception {
        String rutaRelativa = null;

        if (imgPerfilUsuario != null) {
            ServletContext context = ServletActionContext.getServletContext();
            String nombreFichero = nickname + ".png";
            rutaRelativa = "images/fotosPerfil/" + nombreFichero;
            String ruta = context.getRealPath("/") + rutaRelativa;
            File nuevo = new File(ruta);
            FileUtils.copyFile(imgPerfilUsuario, nuevo);
        } else {

            GenericType<Usuario> tipoUser = new GenericType<Usuario>() {
            };
            usuario = userWS.find_XML(tipoUser, id);
            rutaRelativa = usuario.getFoto();
        }

        UsuarioWS userWS = new UsuarioWS();
        Usuario user = new Usuario(Integer.valueOf(id), nombre, apellidos, nickname, password, email, rutaRelativa, fechaNacimiento);
        userWS.edit_JSON(user, id);
        return SUCCESS;
    }

    public String addUsuario() throws Exception {
        String rutaRelativa = null;

        if (imgPerfilUsuario != null) {
            ServletContext context = ServletActionContext.getServletContext();
            String nombreFichero = nickname + ".png";
            rutaRelativa = "images/fotosPerfil/" + nombreFichero;
            String ruta = context.getRealPath("/") + rutaRelativa;
            File nuevo = new File(ruta);
            FileUtils.copyFile(imgPerfilUsuario, nuevo);
        }

        UsuarioWS userWS = new UsuarioWS();
        GenericType<Usuario> tipoUsuario = new GenericType<Usuario>() {
        };
        GenericType<Boolean> tipoBoolean = new GenericType<Boolean>() {
        };

        boolean econtrado = userWS.existeNickname(tipoBoolean, nickname);

        if (econtrado == false) {
            Usuario newUsuario = new Usuario(null, nombre, apellidos, nickname, password, email, false, fechaNacimiento, rutaRelativa);
            userWS.create_XML(newUsuario);

            Usuario usu = userWS.getUsuarioByUsername_XML(tipoUsuario, nickname);
            session = (Map) ActionContext.getContext().get("session");
            session.put("user", usu);

        }

        return SUCCESS;
    }

    @Override
    public void validate() {
        if (this.getNombre() == null || this.getNombre().trim().length() == 0) {
            addFieldError("nombre", "El nombre es obligatorio");
        } else if (this.getNombre().length() > 50) {
            addFieldError("nombre", "Tamaño máximo 50 carácteres");
        }

        if (this.getApellidos() == null || this.getApellidos().trim().length() == 0) {
            addFieldError("apellidos", "El apellido es obligatorio");
        } else if (this.getApellidos().length() > 50) {
            addFieldError("apellidos", "Tamaño máximo 50 carácteres");
        }
        
        GenericType<Boolean> tipoBoolean = new GenericType<Boolean>() {
        };
        if (this.getNickname() == null || this.getNickname().trim().length() == 0) {
            addFieldError("nickname", "El nombre de usuario es obligatorio");
        } else if (this.editUsuario == null) {
            if (userWS.existeNickname(tipoBoolean, this.getNickname()) == true) {
                addFieldError("nickname", "El nombre de usuario ya existe");
            }
        } else if (this.getNickname().length() > 50) {
            addFieldError("nickname", "Tamaño máximo 50 carácteres");
        }

        if (this.getPassword() == null || this.getPassword().trim().length() == 0) {
            addFieldError("password", "La contraseña es obligatoria");
        } else if (this.getPassword().length() < 6) {
            addFieldError("password", "Tamaño mínimo 5 carácteres");
        }

        if (this.getEmail() == null || this.getEmail().trim().length() == 0) {
            addFieldError("email", "El email es obligatorio");
        } else if (!this.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            addFieldError("email", "Formato incorrecto");
        }

        if (this.getFechaNacimiento() == null) {
            addFieldError("fechaNacimiento", "La de nacimiento es obligatorio");
        }

        if (!getFieldErrors().isEmpty()) {
            GenericType<Usuario> tipoUser = new GenericType<Usuario>() {
            };
            usuario = userWS.find_XML(tipoUser, id);
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

    public File getImgPerfilUsuario() {
        return imgPerfilUsuario;
    }

    public void setImgPerfilUsuario(File imgPerfilUsuario) {
        this.imgPerfilUsuario = imgPerfilUsuario;
    }

    public String getEditUsuario() {
        return editUsuario;
    }

    public void setEditUsuario(String editUsuario) {
        this.editUsuario = editUsuario;
    }

}
