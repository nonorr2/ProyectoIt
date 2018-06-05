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
@Table(name = "voto_publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VotoPublicacion.findAll", query = "SELECT v FROM VotoPublicacion v")
    , @NamedQuery(name = "VotoPublicacion.findByTipo", query = "SELECT v FROM VotoPublicacion v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "VotoPublicacion.findByFechaHora", query = "SELECT v FROM VotoPublicacion v WHERE v.fechaHora = :fechaHora")
    , @NamedQuery(name = "VotoPublicacion.findById", query = "SELECT v FROM VotoPublicacion v WHERE v.id = :id")})
public class VotoPublicacion implements Serializable {

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
    @JoinColumn(name = "id_publicacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Publicacion idPublicacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public VotoPublicacion() {
    }

    public VotoPublicacion(Integer id) {
        this.id = id;
    }

    public VotoPublicacion(Integer id, boolean tipo, Date fechaHora) {
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
        if (!(object instanceof VotoPublicacion)) {
            return false;
        }
        VotoPublicacion other = (VotoPublicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.VotoPublicacion[ id=" + id + " ]";
    }
    
}
