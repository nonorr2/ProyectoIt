/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ws.Usuario;

/**
 *
 * @author Nono
 */
@Stateless
@Path("ws.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "webServicePU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12)));
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
        if (super.find(id).getPassword().equals(entity.getPassword())){
            entity.setPassword(super.find(id).getPassword());
        }else{
            entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12)));
        }
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Metodo para obtener los usuarios cuyo nombre corresponda con el nombre
     * pasado como parametro
     *
     * @param nombre
     * @return Lista de usuarios
     */
    @GET
    @Path("/getUsuariosPorNombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> getUsuariosPorNombre(@PathParam("nombre") String nombre) {
        String jpql = "SELECT u FROM Usuario u WHERE u.nombre LIKE '" + nombre + "%'";
        Query query = em.createQuery(jpql);
        List<Usuario> result = query.getResultList();
        return result;
    }

    @GET
    @Path("/login/{usuario}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean login(@PathParam("usuario") String usuario, @PathParam("password") String password) {
        Boolean resultado = null;

        try {
            Usuario user = getUsuarioByUsername(usuario);
            resultado = BCrypt.checkpw(password, user.getPassword());
        } catch (Exception e) {
            resultado = false;
        }

        //String as = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return resultado;
    }

    @GET
    @Path("/getUsuarioByUsername/{username}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario getUsuarioByUsername(@PathParam("username") String username) throws Exception {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nickname= :username");
        query.setParameter("username", username);
        Usuario result = (Usuario) query.getSingleResult();
        return result;
    }

    /**
     * Metodo que devuelve una lista de usuarios de tipo 0 (todos menos los
     * administradores)
     *
     * @return Lista de usuarios
     * @throws Exception
     */
    @GET
    @Path("/getUsuarioTipo")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> getUsuarioTipo() throws Exception {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.tipo = 0");
        List<Usuario> result = (List<Usuario>) query.getResultList();
        return result;
    }
    
    @GET
    @Path("/getUsuariosChat/{id_user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> getUsuariosChat(@PathParam("id_user") Integer id_user) throws Exception {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.tipo = 0 AND u.id != :id_user");
        query.setParameter("id_user", id_user);
        List<Usuario> result = (List<Usuario>) query.getResultList();
        return result;
    }
}
