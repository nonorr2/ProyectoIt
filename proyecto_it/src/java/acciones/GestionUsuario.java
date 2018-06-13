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

    /**
     * Método que hace uso del web service para guardar las modificaciones de un
     * usuario en la BD
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String editUserPersistencia() throws Exception {
        String rutaRelativa = null;

        if (imgPerfilUsuario != null) {
            ServletContext context = ServletActionContext.getServletContext();
            String nicknameUsuario = nickname.replace(" ", "_");
            String nombreFichero = nicknameUsuario + ".png";
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
        userWS.edit_XML(user, id);
        return SUCCESS;
    }

    /**
     * Método que hace uso del web service para guardar a un nuevo usuario en la
     * BD
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String addUsuario() throws Exception {
        String rutaRelativa = null;

        if (imgPerfilUsuario != null) {
            ServletContext context = ServletActionContext.getServletContext();
            String nicknameUsuario = nickname.replace(" ", "_");
            String nombreFichero = nicknameUsuario + ".png";
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

    /**
     * Método para validar los campos del formulario de registrar, perfil y
     * editar usuario del administrador
     */
    @Override
    public void validate() {
        if (this.getNombre() == null || this.getNombre().trim().length() == 0) {
            addFieldError("nombre", getText("nombre.requerido"));
        } else if (this.getNombre().length() > 50) {
            addFieldError("nombre", getText("nombre.tamano"));
        }

        if (this.getApellidos() == null || this.getApellidos().trim().length() == 0) {
            addFieldError("apellidos", getText("apellidos.requerido"));
        } else if (this.getApellidos().length() > 50) {
            addFieldError("apellidos", getText("apellidos.tamano"));
        }

        GenericType<Boolean> tipoBoolean = new GenericType<Boolean>() {
        };
        if (this.getNickname() == null || this.getNickname().trim().length() == 0) {
            addFieldError("nickname", getText("nickname.requerido"));
        } else if (this.editUsuario == null) {
            if (userWS.existeNickname(tipoBoolean, this.getNickname()) == true) {
                addFieldError("nickname", getText("nickname.existe"));
            }
        } else if (this.getNickname().length() > 50) {
            addFieldError("nickname", getText("nickname.tamano"));
        }

        if (this.getPassword() == null || this.getPassword().trim().length() == 0) {
            addFieldError("password", getText("password.requerido"));
        } else if (this.getPassword().length() < 6) {
            addFieldError("password", getText("password.tamano"));
        }

        if (this.getEmail() == null || this.getEmail().trim().length() == 0) {
            addFieldError("email", getText("email.requerido"));
        } else if (!this.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            addFieldError("email", getText("email.formato"));
        }

        if (this.getFechaNacimiento() == null) {
            addFieldError("fechaNacimiento", getText("fechaNacimiento.requerido"));
        }

        if (!getFieldErrors().isEmpty() && id != null) {
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
