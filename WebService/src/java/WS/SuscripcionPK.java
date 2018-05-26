/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nono
 */
@Embeddable
public class SuscripcionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_publicacion")
    private int idPublicacion;

    public SuscripcionPK() {
    }

    public SuscripcionPK(int idUsuario, int idPublicacion) {
        this.idUsuario = idUsuario;
        this.idPublicacion = idPublicacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idPublicacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuscripcionPK)) {
            return false;
        }
        SuscripcionPK other = (SuscripcionPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idPublicacion != other.idPublicacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS.SuscripcionPK[ idUsuario=" + idUsuario + ", idPublicacion=" + idPublicacion + " ]";
    }
    
}
