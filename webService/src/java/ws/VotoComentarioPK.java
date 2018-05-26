/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David
 */
@Embeddable
public class VotoComentarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_comentario")
    private int idComentario;

    public VotoComentarioPK() {
    }

    public VotoComentarioPK(int idUsuario, int idComentario) {
        this.idUsuario = idUsuario;
        this.idComentario = idComentario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idComentario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotoComentarioPK)) {
            return false;
        }
        VotoComentarioPK other = (VotoComentarioPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idComentario != other.idComentario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.VotoComentarioPK[ idUsuario=" + idUsuario + ", idComentario=" + idComentario + " ]";
    }
    
}
