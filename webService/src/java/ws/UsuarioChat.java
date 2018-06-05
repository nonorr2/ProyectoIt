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
 * @author Nono
 */
@Entity
@Table(name = "usuario_chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioChat.findAll", query = "SELECT u FROM UsuarioChat u")
    , @NamedQuery(name = "UsuarioChat.findByIdUsuario", query = "SELECT u FROM UsuarioChat u WHERE u.usuarioChatPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "UsuarioChat.findByIdChat", query = "SELECT u FROM UsuarioChat u WHERE u.usuarioChatPK.idChat = :idChat")
    , @NamedQuery(name = "UsuarioChat.findByFecha", query = "SELECT u FROM UsuarioChat u WHERE u.fecha = :fecha")})
public class UsuarioChat implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioChatPK usuarioChatPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_chat", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Chat chat;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public UsuarioChat() {
    }

    public UsuarioChat(UsuarioChatPK usuarioChatPK) {
        this.usuarioChatPK = usuarioChatPK;
    }

    public UsuarioChat(UsuarioChatPK usuarioChatPK, Date fecha) {
        this.usuarioChatPK = usuarioChatPK;
        this.fecha = fecha;
    }

    public UsuarioChat(int idUsuario, int idChat) {
        this.usuarioChatPK = new UsuarioChatPK(idUsuario, idChat);
    }

    public UsuarioChatPK getUsuarioChatPK() {
        return usuarioChatPK;
    }

    public void setUsuarioChatPK(UsuarioChatPK usuarioChatPK) {
        this.usuarioChatPK = usuarioChatPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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
        hash += (usuarioChatPK != null ? usuarioChatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioChat)) {
            return false;
        }
        UsuarioChat other = (UsuarioChat) object;
        if ((this.usuarioChatPK == null && other.usuarioChatPK != null) || (this.usuarioChatPK != null && !this.usuarioChatPK.equals(other.usuarioChatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.UsuarioChat[ usuarioChatPK=" + usuarioChatPK + " ]";
    }
    
}
