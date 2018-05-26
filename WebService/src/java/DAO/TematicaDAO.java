package DAO;

import POJOs.HibernateUtil;
import POJOs.Tematica;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class TematicaDAO {

    Session session = null;

    public void update(Tematica tematica) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(tematica);
        tx.commit();
    }

    public void remove(Tematica tematica) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(tematica);
        tx.commit();
    }

    public void create(Tematica tematica) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(tematica);
        tx.commit();
    }

    public Tematica getTematica(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tematica where id='" + id + "'");
        Tematica tematica = (Tematica) q.uniqueResult();
        tx.commit();
        return tematica;
    }

    public List<Tematica> getTematicas() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tematica");
        List<Tematica> tematicas = (List<Tematica>) q.list();
        tx.commit();
        return tematicas;
    }
}