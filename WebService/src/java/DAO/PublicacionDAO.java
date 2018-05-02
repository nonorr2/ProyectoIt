package DAO;

import POJOs.HibernateUtil;
import POJOs.Publicacion;
import POJOs.Tematica;
import POJOs.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PublicacionDAO {

    Session session = null;

    public void update(Publicacion publicacion) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(publicacion);
        tx.commit();
    }

    public void remove(Publicacion publicacion) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(publicacion);
        tx.commit();
    }

    public void create(Publicacion publicacion) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(publicacion);
        tx.commit();
    }

    public Publicacion getPublicacion(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Publicacion where id='" + id + "'");
        Publicacion publicacion = (Publicacion) q.uniqueResult();
        tx.commit();
        return publicacion;
    }

    public List<Publicacion> getPublicaciones(Tematica tematica) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Publicacion where tematica='" + tematica + "'");
        List<Publicacion> publicaciones = (List<Publicacion>) q.list();
        tx.commit();
        return publicaciones;
    }

    public List<Publicacion> getPublicaciones(Usuario user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Publicacion where usuario='" + user + "'");
        List<Publicacion> publicaciones = (List<Publicacion>) q.list();
        tx.commit();
        return publicaciones;
    }
}
