/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "suscripcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suscripcion.findAll", query = "SELECT s FROM Suscripcion s")
    , @NamedQuery(name = "Suscripcion.findByFechaHora", query = "SELECT s FROM Suscripcion s WHERE s.fechaHora = :fechaHora")
    , @NamedQuery(name = "Suscripcion.findByIdUsuario", query = "SELECT s FROM Suscripcion s WHERE s.suscripcionPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "Suscripcion.findByIdPublicacion", query = "SELECT s FROM Suscripcion s WHERE s.suscripcionPK.idPublicacion = :idPublicacion")})
public class Suscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SuscripcionPK suscripcionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "id_publicacion", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Publicacion publicacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Suscripcion() {
    }

    public Suscripcion(SuscripcionPK suscripcionPK) {
        this.suscripcionPK = suscripcionPK;
    }

    public Suscripcion(SuscripcionPK suscripcionPK, Date fechaHora) {
        this.suscripcionPK = suscripcionPK;
        this.fechaHora = fechaHora;
    }

    public Suscripcion(int idUsuario, int idPublicacion) {
        this.suscripcionPK = new SuscripcionPK(idUsuario, idPublicacion);
    }

    public SuscripcionPK getSuscripcionPK() {
        return suscripcionPK;
    }

    public void setSuscripcionPK(SuscripcionPK suscripcionPK) {
        this.suscripcionPK = suscripcionPK;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suscripcionPK != null ? suscripcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suscripcion)) {
            return false;
        }
        Suscripcion other = (Suscripcion) object;
        if ((this.suscripcionPK == null && other.suscripcionPK != null) || (this.suscripcionPK != null && !this.suscripcionPK.equals(other.suscripcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.Suscripcion[ suscripcionPK=" + suscripcionPK + " ]";
    }
    
}
