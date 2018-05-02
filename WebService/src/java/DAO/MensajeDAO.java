package DAO;

import POJOs.Chat;
import POJOs.HibernateUtil;
import POJOs.Mensaje;
import POJOs.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class MensajeDAO {

    Session session = null;

    public void update(Mensaje mensaje) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(mensaje);
        tx.commit();
    }

    public void remove(Mensaje mensaje) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(mensaje);
        tx.commit();
    }

    public void create(Mensaje mensaje) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(mensaje);
        tx.commit();
    }

    public Mensaje getPublicacion(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Mensaje where id='" + id + "'");
        Mensaje mensaje = (Mensaje) q.uniqueResult();
        tx.commit();
        return mensaje;
    }

    public List<Mensaje> getMensajes(Chat chat) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Mensaje where chat='" + chat + "'");
        List<Mensaje> mensajes = (List<Mensaje>) q.list();
        tx.commit();
        return mensajes;
    }

    public List<Mensaje> getMensajes(Chat chat, Usuario user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Mensaje where chat='" + chat + "' and usuario='"+ user + "'");
        List<Mensaje> mensajes = (List<Mensaje>) q.list();
        tx.commit();
        return mensajes;
    }
}
