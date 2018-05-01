package POJOs;
// Generated 01-may-2018 18:31:57 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Chat generated by hbm2java
 */
public class Chat  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private Date fechaHora;
     private Set usuarios = new HashSet(0);
     private Set mensajes = new HashSet(0);

    public Chat() {
    }

	
    public Chat(String nombre, Date fechaHora) {
        this.nombre = nombre;
        this.fechaHora = fechaHora;
    }
    public Chat(String nombre, Date fechaHora, Set usuarios, Set mensajes) {
       this.nombre = nombre;
       this.fechaHora = fechaHora;
       this.usuarios = usuarios;
       this.mensajes = mensajes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaHora() {
        return this.fechaHora;
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }
    public Set getMensajes() {
        return this.mensajes;
    }
    
    public void setMensajes(Set mensajes) {
        this.mensajes = mensajes;
    }




}


