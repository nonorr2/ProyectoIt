/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

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
 * @author Nono
 */
@Entity
@Table(name = "voto_comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VotoComentario.findAll", query = "SELECT v FROM VotoComentario v")
    , @NamedQuery(name = "VotoComentario.findByTipo", query = "SELECT v FROM VotoComentario v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "VotoComentario.findByFechaHora", query = "SELECT v FROM VotoComentario v WHERE v.fechaHora = :fechaHora")
    , @NamedQuery(name = "VotoComentario.findByIdUsuario", query = "SELECT v FROM VotoComentario v WHERE v.votoComentarioPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "VotoComentario.findByIdComentario", query = "SELECT v FROM VotoComentario v WHERE v.votoComentarioPK.idComentario = :idComentario")})
public class VotoComentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VotoComentarioPK votoComentarioPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private boolean tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "id_comentario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comentario comentario;

    public VotoComentario() {
    }

    public VotoComentario(VotoComentarioPK votoComentarioPK) {
        this.votoComentarioPK = votoComentarioPK;
    }

    public VotoComentario(VotoComentarioPK votoComentarioPK, boolean tipo, Date fechaHora) {
        this.votoComentarioPK = votoComentarioPK;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
    }

    public VotoComentario(int idUsuario, int idComentario) {
        this.votoComentarioPK = new VotoComentarioPK(idUsuario, idComentario);
    }

    public VotoComentarioPK getVotoComentarioPK() {
        return votoComentarioPK;
    }

    public void setVotoComentarioPK(VotoComentarioPK votoComentarioPK) {
        this.votoComentarioPK = votoComentarioPK;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (votoComentarioPK != null ? votoComentarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotoComentario)) {
            return false;
        }
        VotoComentario other = (VotoComentario) object;
        if ((this.votoComentarioPK == null && other.votoComentarioPK != null) || (this.votoComentarioPK != null && !this.votoComentarioPK.equals(other.votoComentarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS.VotoComentario[ votoComentarioPK=" + votoComentarioPK + " ]";
    }
    
}
