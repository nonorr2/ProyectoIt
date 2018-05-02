package DAO;

import POJOs.Comentario;
import POJOs.HibernateUtil;
import POJOs.Publicacion;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ComentarioDAO {

    Session session = null;

    public void update(Comentario comentario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(comentario);
        tx.commit();
    }

    public void remove(Comentario comentario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(comentario);
        tx.commit();
    }

    public void create(Comentario comentario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(comentario);
        tx.commit();
    }

    public Comentario getComentario(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Comentario where id='" + id + "'");
        Comentario comentario = (Comentario) q.uniqueResult();
        tx.commit();
        return comentario;
    }

    public List<Comentario> getComentarios(Publicacion publicacion) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Comentario where publicacion='" + publicacion + "'");
        List<Comentario> comentarios = (List<Comentario>) q.list();
        tx.commit();
        return comentarios;
    }

}
