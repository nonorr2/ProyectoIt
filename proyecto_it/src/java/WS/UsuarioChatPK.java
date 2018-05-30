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
public class UsuarioChatPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_chat")
    private int idChat;

    public UsuarioChatPK() {
    }

    public UsuarioChatPK(int idUsuario, int idChat) {
        this.idUsuario = idUsuario;
        this.idChat = idChat;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idChat;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioChatPK)) {
            return false;
        }
        UsuarioChatPK other = (UsuarioChatPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idChat != other.idChat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS.UsuarioChatPK[ idUsuario=" + idUsuario + ", idChat=" + idChat + " ]";
    }
    
}
