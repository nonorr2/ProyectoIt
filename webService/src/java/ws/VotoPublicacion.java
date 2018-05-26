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
@Table(name = "voto_publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VotoPublicacion.findAll", query = "SELECT v FROM VotoPublicacion v")
    , @NamedQuery(name = "VotoPublicacion.findByTipo", query = "SELECT v FROM VotoPublicacion v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "VotoPublicacion.findByFechaHora", query = "SELECT v FROM VotoPublicacion v WHERE v.fechaHora = :fechaHora")
    , @NamedQuery(name = "VotoPublicacion.findByIdUsuario", query = "SELECT v FROM VotoPublicacion v WHERE v.votoPublicacionPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "VotoPublicacion.findByIdPublicacion", query = "SELECT v FROM VotoPublicacion v WHERE v.votoPublicacionPK.idPublicacion = :idPublicacion")})
public class VotoPublicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VotoPublicacionPK votoPublicacionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private boolean tipo;
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

    public VotoPublicacion() {
    }

    public VotoPublicacion(VotoPublicacionPK votoPublicacionPK) {
        this.votoPublicacionPK = votoPublicacionPK;
    }

    public VotoPublicacion(VotoPublicacionPK votoPublicacionPK, boolean tipo, Date fechaHora) {
        this.votoPublicacionPK = votoPublicacionPK;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
    }

    public VotoPublicacion(int idUsuario, int idPublicacion) {
        this.votoPublicacionPK = new VotoPublicacionPK(idUsuario, idPublicacion);
    }

    public VotoPublicacionPK getVotoPublicacionPK() {
        return votoPublicacionPK;
    }

    public void setVotoPublicacionPK(VotoPublicacionPK votoPublicacionPK) {
        this.votoPublicacionPK = votoPublicacionPK;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
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
        hash += (votoPublicacionPK != null ? votoPublicacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotoPublicacion)) {
            return false;
        }
        VotoPublicacion other = (VotoPublicacion) object;
        if ((this.votoPublicacionPK == null && other.votoPublicacionPK != null) || (this.votoPublicacionPK != null && !this.votoPublicacionPK.equals(other.votoPublicacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.VotoPublicacion[ votoPublicacionPK=" + votoPublicacionPK + " ]";
    }
    
}
