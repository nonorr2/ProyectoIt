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

        ComentarioWS comentarioClient = new ComentarioWS();
        GenericType<Comentario> tipoComentario = new GenericType<Comentario>() {
        };
        Comentario coment = comentarioClient.find_XML(tipoComentario, String.valueOf(idComentario));

        VotoComentario vt = new VotoComentario(null, true, new Date());
        vt.setIdUsuario(user);
        vt.setIdComentario(coment);

        VotoComentarioWS votoComentarioClient = new VotoComentarioWS();
        GenericType<Integer> tipoInteger = new GenericType<Integer>() {
        };

        Integer exist = votoComentarioClient.haVotado(tipoInteger, String.valueOf(user.getId()), String.valueOf(idComentario));
        if (exist == null) {
            votoComentarioClient.create_XML(vt);
        } else {
            vt.setId(exist);
            votoComentarioClient.edit_JSON(vt, String.valueOf(exist));
        }
        return SUCCESS;
    }

    public String votoNegativo() throws Exception {
        Usuario user = (Usuario) loginLogout.session.get("user");

        ComentarioWS comentarioClient = new ComentarioWS();
        GenericType<Comentario> tipoComentario = new GenericType<Comentario>() {
        };
        Comentario coment = comentarioClient.find_XML(tipoComentario, String.valueOf(idComentario));

        VotoComentario vtp = new VotoComentario(null, true, new Date());
        vtp.setIdUsuario(user);
        vtp.setIdComentario(coment);

        VotoComentario vtn = new VotoComentario(null, false, new Date());
        vtn.setIdUsuario(user);
        vtn.setIdComentario(coment);

        VotoComentarioWS votoComentarioClient = new VotoComentarioWS();
        GenericType<Integer> tipoInteger = new GenericType<Integer>() {
        };

        Integer exist = votoComentarioClient.haVotado(tipoInteger, String.valueOf(user.getId()), String.valueOf(idComentario));
        if (exist == null) {
            votoComentarioClient.create_XML(vtn);
        } else {
            vtn.setId(exist);
            votoComentarioClient.edit_JSON(vtn, String.valueOf(exist));
        }
        return SUCCESS;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

}
