package DAO;

import POJOs.HibernateUtil;
import POJOs.Publicacion;
import POJOs.VotoPublicacion;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class VotoPublicacionDAO {

    Session session = null;

    public void update(VotoPublicacion voto) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(voto);
        tx.commit();
    }

    public void remove(VotoPublicacion voto) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(voto);
        tx.commit();
    }

    public void create(VotoPublicacion voto) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(voto);
        tx.commit();
    }

    public List<VotoPublicacion> getVotosPublicacion(Publicacion publi) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from VotosPublicacion where publicacion='" + publi + "'");
        List<VotoPublicacion> votos = (List<VotoPublicacion>) q.list();
        tx.commit();
        return votos;
    }

    public List<VotoPublicacion> getVotosPublicacionPositivos(Publicacion publi) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from VotosPublicacion where tipo='true' and publicacion='" + publi + "'");
        List<VotoPublicacion> votos = (List<VotoPublicacion>) q.list();
        tx.commit();
        return votos;
    }

    public List<VotoPublicacion> getVotosPublicacionNegativos(Publicacion publi) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from VotosPublicacion where tipo='false' and publicacion='" + publi + "'");
        List<VotoPublicacion> votos = (List<VotoPublicacion>) q.list();
        tx.commit();
        return votos;
    }
}
