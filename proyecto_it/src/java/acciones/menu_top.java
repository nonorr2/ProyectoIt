package acciones;

import WS.Publicacion;
import WS.PublicacionWS;
import WS.Tematica;
import WS.TematicaWS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class menu_top extends ActionSupport {

    private List<Tematica> tematicas;
    private List<Publicacion> publicaciones;

    public menu_top() {
    }

    @Override
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String inicio() throws Exception {
        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.getTematicasMasPopulares_JSON(tipoTematica);
        return SUCCESS;
    }

    public String temas() throws Exception {
        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.findAll_JSON(tipoTematica);
        return SUCCESS;
    }

    public String publicaciones() throws Exception {
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        PublicacionWS publicacionClient = new PublicacionWS();
        publicaciones = (List<Publicacion>) publicacionClient.findAll_JSON(tipoPublicacion);
        return SUCCESS;
    }

    public String registro() throws Exception {
        return SUCCESS;
    }

    public String perfil() throws Exception {
        return SUCCESS;
    }

    public List<Tematica> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<Tematica> tematicas) {
        this.tematicas = tematicas;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

}
