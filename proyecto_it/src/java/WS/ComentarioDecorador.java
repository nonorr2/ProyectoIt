package WS;

import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

public class ComentarioDecorador {
    private Comentario comentario;
    private Integer votosPositivos;
    private Integer votosNegativos;

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Integer getVotosPositivos() {
        return votosPositivos;
    }

    public void setVotosPositivos(Integer votosPositivos) {
        this.votosPositivos = votosPositivos;
    }

    public Integer getVotosNegativos() {
        return votosNegativos;
    }

    public void setVotosNegativos(Integer votosNegativos) {
        this.votosNegativos = votosNegativos;
    }
    
    public Integer getId() {
        return comentario.getId();
    }

    public void setId(Integer id) {
        this.comentario.setId(id);
    }

    public Date getFechaHoraModificacion() {
        return comentario.getFechaHoraModificacion();
    }

    public void setFechaHoraModificacion(Date fechaHoraModificacion) {
        this.comentario.setFechaHoraModificacion(fechaHoraModificacion);
    }

    public String getContenido() {
        return comentario.getContenido();
    }

    public void setContenido(String contenido) {
        this.comentario.setContenido(contenido);
    }

    public Date getFechaHoraCreacion() {
        return comentario.getFechaHoraCreacion();
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.comentario.setFechaHoraCreacion(fechaHoraCreacion);
    }

    @XmlTransient
    public Collection<VotoComentario> getVotoComentarioCollection() {
        return comentario.getVotoComentarioCollection();
    }

    public void setVotoComentarioCollection(Collection<VotoComentario> votoComentarioCollection) {
        this.comentario.setVotoComentarioCollection(votoComentarioCollection);
    }

    public Publicacion getIdPublicacion() {
        return comentario.getIdPublicacion();
    }

    public void setIdPublicacion(Publicacion idPublicacion) {
        this.comentario.setIdPublicacion(idPublicacion);
    }

    public Usuario getIdUsuario() {
        return comentario.getIdUsuario();
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.comentario.setIdUsuario(idUsuario);
    }
}
