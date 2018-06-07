/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
public class TematicaDecorado {
    private Tematica tematicas;
    private Integer numPublicacionesDeUnaTematica;
    
    
    
    public Integer getId() {
        return tematicas.getId();
    }

    public void setId(Integer id) {
        this.tematicas.setId(id);
    }

    public String getNombre() {
        return tematicas.getNombre();
    }

    public void setNombre(String nombre) {
        this.tematicas.setNombre(nombre);
    }

    public String getImagen() {
        return tematicas.getImagen();
    }

    public void setImagen(String imagen) {
        this.tematicas.setImagen(imagen);
    }

    @XmlTransient
    public Collection<Publicacion> getPublicacionCollection() {
        return tematicas.getPublicacionCollection();
    }

    public void setPublicacionCollection(Collection<Publicacion> publicacionCollection) {
        this.tematicas.setPublicacionCollection(publicacionCollection);
    }

    public Tematica getTematicas() {
        return tematicas;
    }

    public void setTematicas(Tematica tematicas) {
        this.tematicas = tematicas;
    }

    public Integer getNumPublicacionesDeUnaTematica() {
        return numPublicacionesDeUnaTematica;
    }

    public void setNumPublicacionesDeUnaTematica(Integer numPublicacionesDeUnaTematica) {
        this.numPublicacionesDeUnaTematica = numPublicacionesDeUnaTematica;
    }
    
    
}
