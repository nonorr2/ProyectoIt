/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lydia
 */
public class PublicacionDecorado {

    private Publicacion publicacion;
    private Comentario ultimoComentario;
    private Integer numVotosPositivosPublicacion;
    private Integer numVotosNegativosPublicacion;
    private Integer numComentarios;

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Comentario getUltimoComentario() {
        return ultimoComentario;
    }

    public void setUltimoComentario(Comentario ultimoComentario) {
        this.ultimoComentario = ultimoComentario;
    }

    public Integer getId() {
        return publicacion.getId();
    }

    public void setId(Integer id) {
        this.publicacion.setId(id);
    }

    public String getTitulo() {
        return publicacion.getTitulo();
    }

    public void setTitulo(String titulo) {
        this.publicacion.setTitulo(titulo);
    }

    public String getContenido() {
        return publicacion.getContenido();
    }

    public void setContenido(String contenido) {
        this.publicacion.setContenido(contenido);
    }

    public Date getFechaHoraCreacion() {
        return publicacion.getFechaHoraCreacion();
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.publicacion.setFechaHoraCreacion(fechaHoraCreacion);
    }

    public Date getFechaHoraModificacion() {
        return publicacion.getFechaHoraModificacion();
    }

    public void setFechaHoraModificacion(Date fechaHoraModificacion) {
        this.publicacion.setFechaHoraModificacion(fechaHoraModificacion);
    }

    public String getRuta() {
        return publicacion.getRuta();
    }

    public void setRuta(String ruta) {
        this.publicacion.setRuta(ruta);
    }

    public String getFoto() {
        return publicacion.getFoto();
    }

    public void setFoto(String foto) {
        this.publicacion.setFoto(foto);
    }

    @XmlTransient
    public Collection<Suscripcion> getSuscripcionCollection() {
        return publicacion.getSuscripcionCollection();
    }

    public void setSuscripcionCollection(Collection<Suscripcion> suscripcionCollection) {
        this.publicacion.setSuscripcionCollection(suscripcionCollection);
    }

    public Collection<Comentario> getComentarioCollection() {
        return publicacion.getComentarioCollection();
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.publicacion.setComentarioCollection(comentarioCollection);
    }

    public Collection<VotoPublicacion> getVotoPublicacionCollection() {
        return publicacion.getVotoPublicacionCollection();
    }

    public void setVotoPublicacionCollection(Collection<VotoPublicacion> votoPublicacionCollection) {
        this.publicacion.setVotoPublicacionCollection(votoPublicacionCollection);
    }

    public Tematica getIdTematica() {
        return publicacion.getIdTematica();
    }

    public void setIdTematica(Tematica idTematica) {
        this.publicacion.setIdTematica(idTematica);
    }

    public Usuario getIdUsuario() {
        return publicacion.getIdUsuario();
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.publicacion.setIdUsuario(idUsuario);
    }

    public Integer getNumVotosPositivosPublicacion() {
        return numVotosPositivosPublicacion;
    }

    public void setNumVotosPositivosPublicacion(Integer numVotosPositivosPublicacion) {
        this.numVotosPositivosPublicacion = numVotosPositivosPublicacion;
    }

    public Integer getNumVotosNegativosPublicacion() {
        return numVotosNegativosPublicacion;
    }

    public void setNumVotosNegativosPublicacion(Integer numVotosNegativosPublicacion) {
        this.numVotosNegativosPublicacion = numVotosNegativosPublicacion;
    }

    public Integer getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(Integer numComentarios) {
        this.numComentarios = numComentarios;
    }
}
