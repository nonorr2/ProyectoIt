package acciones;

import WS.Comentario;
import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class PublicacionAction extends ActionSupport {

    private Publicacion publicacion;
    private List<Comentario> comentarios;
    private String id_publi;

    public PublicacionAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String verPublicacion() throws Exception {
        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {
        };
        PublicacionWS publicacionCliente = new PublicacionWS();
        publicacion = publicacionCliente.find_XML(tipoPublicacion, id_publi);

        GenericType<List<Comentario>> tipoComentario = new GenericType<List<Comentario>>() {
        };
        ComentarioWS comentarioCliente = new ComentarioWS();
        comentarios = comentarioCliente.getComentariosPublicacion_XML(tipoComentario, id_publi);

        return SUCCESS;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getId_publi() {
        return id_publi;
    }

    public void setId_publi(String id_publi) {
        this.id_publi = id_publi;
    }

}
