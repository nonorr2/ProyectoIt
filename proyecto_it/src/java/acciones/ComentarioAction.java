package acciones;

import WS.Comentario;
import WS.ComentarioDecorador;
import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionWS;
import WS.Usuario;
import WS.VotoComentario;
import WS.VotoComentarioWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class ComentarioAction extends ActionSupport {

    private Integer idComentario;
    private String id_publi;
    private String textoComentario;

    private List<ComentarioDecorador> comentarios;
    private Publicacion publicacion;

    public ComentarioAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void validate() {
        if (textoComentario.trim().length() == 0) {
            addFieldError("textoComentario", "El comentario no puede estar vacio");

            comentarios = new ArrayList<>();

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
        }
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

    public String comentarPublicacion() throws Exception {
        Usuario user = (Usuario) loginLogout.session.get("user");

        ComentarioWS comentarioClient = new ComentarioWS();
        PublicacionWS publicacionClient = new PublicacionWS();
        GenericType<Publicacion> tipoPublicacion = new GenericType<Publicacion>() {
        };

        Publicacion publi = publicacionClient.find_JSON(tipoPublicacion, id_publi);

        Comentario comment = new Comentario(null, new Date(), textoComentario, new Date());
        comment.setIdUsuario(user);
        comment.setIdPublicacion(publi);

        comentarioClient.create_JSON(comment);

        return SUCCESS;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public String getId_publi() {
        return id_publi;
    }

    public void setId_publi(String id_publi) {
        this.id_publi = id_publi;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public List<ComentarioDecorador> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDecorador> comentarios) {
        this.comentarios = comentarios;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

}
