/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nono
 */
@Entity
@Table(name = "comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c")
    , @NamedQuery(name = "Comentario.findById", query = "SELECT c FROM Comentario c WHERE c.id = :id")
    , @NamedQuery(name = "Comentario.findByFechaHoraModificacion", query = "SELECT c FROM Comentario c WHERE c.fechaHoraModificacion = :fechaHoraModificacion")
    , @NamedQuery(name = "Comentario.findByContenido", query = "SELECT c FROM Comentario c WHERE c.contenido = :contenido")
    , @NamedQuery(name = "Comentario.findByFechaHoraCreacion", query = "SELECT c FROM Comentario c WHERE c.fechaHoraCreacion = :fechaHoraCreacion")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "contenido")
    private String contenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comentario")
    private Collection<VotoComentario> votoComentarioCollection;
    @JoinColumn(name = "id_publicacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Publicacion idPublicacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Comentario() {
    }

    public Comentario(Integer id) {
        this.id = id;
    }

    public Comentario(Integer id, Date fechaHoraModificacion, String contenido, Date fechaHoraCreacion) {
        this.id = id;
        this.fechaHoraModificacion = fechaHoraModificacion;
        this.contenido = contenido;
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaHoraModificacion() {
        return fechaHoraModificacion;
    }

    public void setFechaHoraModificacion(Date fechaHoraModificacion) {
        this.fechaHoraModificacion = fechaHoraModificacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    @XmlTransient
    public Collection<VotoComentario> getVotoComentarioCollection() {
        return votoComentarioCollection;
    }

    public void setVotoComentarioCollection(Collection<VotoComentario> votoComentarioCollection) {
        this.votoComentarioCollection = votoComentarioCollection;
    }

    public Publicacion getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Publicacion idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS.Comentario[ id=" + id + " ]";
    }
    
}
