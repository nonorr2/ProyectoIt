package acciones;

import WS.Comentario;
import WS.ComentarioWS;
import WS.Usuario;
import WS.VotoComentario;
import WS.VotoComentarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import javax.ws.rs.core.GenericType;

public class ComentarioAction extends ActionSupport {

    private Integer idComentario;
    private String id_publi;

    public ComentarioAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String votoPositivo() throws Exception {
        Usuario user = (Usuario) loginLogout.session.get("user");
//
//        ComentarioWS comentarioClient = new ComentarioWS();
//        GenericType<Comentario> tipoComentario = new GenericType<Comentario>() {
//        };
//        Comentario coment = comentarioClient.find_XML(tipoComentario, String.valueOf(idComentario));
//
//        VotoComentario vt = new VotoComentario();
//        vt.setVotoComentarioPK(new VotoComentarioPK(user.getId(), idComentario));
//        vt.setFechaHora(new Date());
//        vt.setTipo(true);
//        vt.setUsuario(user);
//        vt.setComentario(coment);
//
//        VotoComentarioWS votoComentarioClient = new VotoComentarioWS();
//        votoComentarioClient.create_XML(vt);
        return SUCCESS;
    }

    public String votoNegativo() throws Exception {
        Usuario user = (Usuario) loginLogout.session.get("user");
//
//        ComentarioWS comentarioClient = new ComentarioWS();
//        GenericType<Comentario> tipoComentario = new GenericType<Comentario>() {
//        };
//        Comentario coment = comentarioClient.find_XML(tipoComentario, String.valueOf(idComentario));
//
//        VotoComentario vt = new VotoComentario();
//        vt.setVotoComentarioPK(new VotoComentarioPK(user.getId(), idComentario));
//        vt.setFechaHora(new Date());
//        vt.setTipo(false);
//        vt.setUsuario(user);
//        vt.setComentario(coment);
//
//        VotoComentarioWS votoComentarioClient = new VotoComentarioWS();
//        votoComentarioClient.create_XML(vt);
        return SUCCESS;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

}
