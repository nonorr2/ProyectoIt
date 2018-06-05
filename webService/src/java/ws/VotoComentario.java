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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    , @NamedQuery(name = "VotoComentario.findById", query = "SELECT v FROM VotoComentario v WHERE v.id = :id")})
public class VotoComentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private boolean tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_comentario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comentario idComentario;

    public VotoComentario() {
    }

    public VotoComentario(Integer id) {
        this.id = id;
    }

    public VotoComentario(Integer id, boolean tipo, Date fechaHora) {
        this.id = id;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Comentario getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Comentario idComentario) {
        this.idComentario = idComentario;
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
        if (!(object instanceof VotoComentario)) {
            return false;
        }
        VotoComentario other = (VotoComentario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.VotoComentario[ id=" + id + " ]";
    }
    
}
