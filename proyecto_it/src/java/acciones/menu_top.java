package acciones;

import WS.ComentarioWS;
import WS.Publicacion;
import WS.PublicacionDecorado;
import WS.PublicacionWS;
import WS.Tematica;
import WS.TematicaWS;
import WS.VotoPublicacionWS;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class menu_top extends ActionSupport {

    private List<Tematica> tematicas;
    private List<PublicacionDecorado> publicaciones;
    private String filtroPublicacion;

    public menu_top() {
    }

    @Override
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método para obtener las temáticas mas populares
     *
     * @return
     * @throws Exception
     */
    public String inicio() throws Exception {
        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.getTematicasMasPopulares_XML(tipoTematica);
        return SUCCESS;
    }

    /**
     * Método para obtener todas las temáticas del sistema
     *
     * @return
     * @throws Exception
     */
    public String temas() throws Exception {
        GenericType<List<Tematica>> tipoTematica = new GenericType<List<Tematica>>() {
        };
        TematicaWS tematicaClient = new TematicaWS();
        tematicas = (List<Tematica>) tematicaClient.findAll_XML(tipoTematica);
        return SUCCESS;
    }

    /**
     * Método para obtener todas las publicaciones del sistema
     *
     * @return
     * @throws Exception
     */
    public String publicaciones() throws Exception {
        GenericType<List<Publicacion>> tipoPublicacion = new GenericType<List<Publicacion>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {
        };

        PublicacionWS publicacionClient = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();

        List<Publicacion> listPublicaciones = publicacionClient.findAll_XML(tipoPublicacion);
        this.publicaciones = new ArrayList<PublicacionDecorado>();

        for (Publicacion publicacion : listPublicaciones) {
            PublicacionDecorado publicacionDecorado = new PublicacionDecorado();
            Long numComentarios = comentarioCliente.getNumComentarios(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosPositivos = votoPublicacionCliente.getVotosPositivos(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosNegativos = votoPublicacionCliente.getVotosNegativos(tipoLong, String.valueOf(publicacion.getId()));
            publicacionDecorado.setPublicacion(publicacion);
            publicacionDecorado.setNumComentarios(numComentarios.intValue());
            publicacionDecorado.setNumVotosPositivosPublicacion(numVotosPositivos.intValue());
            publicacionDecorado.setNumVotosNegativosPublicacion(numVotosNegativos.intValue());
            this.publicaciones.add(publicacionDecorado);
        }

        return SUCCESS;
    }

    /**
     * Método para filtrar las publicaciones por su nombre (método para el
     * usuario no logeado)
     *
     * @return
     * @throws Exception
     */
    public String filtroPubli() throws Exception {
        GenericType<List<Publicacion>> tipoPublicacionAdmin = new GenericType<List<Publicacion>>() {
        };
        GenericType<Long> tipoLong = new GenericType<Long>() {
        };

        PublicacionWS publicacionClient = new PublicacionWS();
        ComentarioWS comentarioCliente = new ComentarioWS();
        VotoPublicacionWS votoPublicacionCliente = new VotoPublicacionWS();
        List<Publicacion> listPublicaciones;

        if (filtroPublicacion.equals("")) {
            listPublicaciones = publicacionClient.findAll_XML(tipoPublicacionAdmin);
        } else {
            listPublicaciones = publicacionClient.getPublicacionPorTitulo_XML(tipoPublicacionAdmin, filtroPublicacion);
        }

        this.publicaciones = new ArrayList<PublicacionDecorado>();

        for (Publicacion publicacion : listPublicaciones) {
            PublicacionDecorado publicacionDecorado = new PublicacionDecorado();
            Long numComentarios = comentarioCliente.getNumComentarios(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosPositivos = votoPublicacionCliente.getVotosPositivos(tipoLong, String.valueOf(publicacion.getId()));
            Long numVotosNegativos = votoPublicacionCliente.getVotosNegativos(tipoLong, String.valueOf(publicacion.getId()));
            publicacionDecorado.setPublicacion(publicacion);
            publicacionDecorado.setNumComentarios(numComentarios.intValue());
            publicacionDecorado.setNumVotosPositivosPublicacion(numVotosPositivos.intValue());
            publicacionDecorado.setNumVotosNegativosPublicacion(numVotosNegativos.intValue());
            this.publicaciones.add(publicacionDecorado);
        }

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

    public List<PublicacionDecorado> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionDecorado> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getFiltroPublicacion() {
        return filtroPublicacion;
    }

    public void setFiltroPublicacion(String filtroPublicacion) {
        this.filtroPublicacion = filtroPublicacion;
    }

}
