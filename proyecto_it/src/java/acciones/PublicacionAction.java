package acciones;

import WS.Comentario;
import WS.ComentarioDecorador;
import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionWS;
import WS.VotoComentarioWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class PublicacionAction extends ActionSupport {

    private Publicacion publicacion;
    private List<ComentarioDecorador> comentarios;
    private String id_publi;

    public PublicacionAction() {
        comentarios = new ArrayList<>();
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método para obtener todos los comentarios de una publicación.
     *
     * @return
     * @throws Exception
     */
    public String verPublicacion() throws Exception {
        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {
        };
        PublicacionWS publicacionCliente = new PublicacionWS();
        publicacion = publicacionCliente.find_XML(tipoPublicacion, id_publi);

        GenericType<List<Comentario>> tipoComentario = new GenericType<List<Comentario>>() {
        };
        ComentarioWS comentarioCliente = new ComentarioWS();
        List<Comentario> listaComentarios = comentarioCliente.getComentariosPublicacion_XML(tipoComentario, id_publi);

        VotoComentarioWS votoComentarioClient = new VotoComentarioWS();
        GenericType<Long> tipoVotoComentario = new GenericType<Long>() {
        };

        for (Comentario comentario : listaComentarios) {
            ComentarioDecorador aux = new ComentarioDecorador();
            aux.setComentario(comentario);
            aux.setVotosPositivos(votoComentarioClient.getVotosPositivos(tipoVotoComentario, String.valueOf(comentario.getId())).intValue());
            aux.setVotosNegativos(votoComentarioClient.getVotosNegativos(tipoVotoComentario, String.valueOf(comentario.getId())).intValue());
            comentarios.add(aux);
        }

        return SUCCESS;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public List<ComentarioDecorador> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDecorador> comentarios) {
        this.comentarios = comentarios;
    }

    public String getId_publi() {
        return id_publi;
    }

    public void setId_publi(String id_publi) {
        this.id_publi = id_publi;
    }

}
