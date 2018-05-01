package DAO;

import POJOs.HibernateUtil;
import POJOs.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAO {

    Session session = null;

    public void update(Usuario user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
    }

    public void remove(Usuario user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
    }

    public void create(Usuario user) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }

    public Usuario getUser(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Usuario where id='" + id + "'");
        Usuario user = (Usuario) q.uniqueResult();
        tx.commit();
        return user;
    }

    public List<Usuario> getUsers() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Usuario");
        List<Usuario> users = (List<Usuario>) q.list();
        tx.commit();
        return users;
    }
}
