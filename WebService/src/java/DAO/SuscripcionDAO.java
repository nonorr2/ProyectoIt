package DAO;

import POJOs.HibernateUtil;
import POJOs.Publicacion;
import POJOs.Suscripcion;
import POJOs.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class SuscripcionDAO {

    Session session = null;

    public void update(Suscripcion suscripcion) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(suscripcion);
        tx.commit();
    }

    public void remove(Suscripcion suscripcion) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(suscripcion);
        tx.commit();
    }

    public void create(Suscripcion suscripcion) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(suscripcion);
        tx.commit();
    }

    public Suscripcion getSuscripcion(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Suscripcion where id='" + id + "'");
        Suscripcion suscripcion = (Suscripcion) q.uniqueResult();
        tx.commit();
        return suscripcion;
    }

    public List<Suscripcion> getSuscripciones(Usuario user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Suscripcion where usuario='" + user + "'");
        List<Suscripcion> suscripciones = (List<Suscripcion>) q.list();
        tx.commit();
        return suscripciones;
    }
    
    public List<Suscripcion> getSuscripciones(Publicacion publi) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Suscripcion where publicacion='" + publi + "'");
        List<Suscripcion> suscripciones = (List<Suscripcion>) q.list();
        tx.commit();
        return suscripciones;
    }
}