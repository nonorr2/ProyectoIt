package DAO;

import POJOs.Comentario;
import POJOs.HibernateUtil;
import POJOs.VotoComentario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class VotoComentarioDAO {

    Session session = null;

    public void update(VotoComentario voto) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(voto);
        tx.commit();
    }

    public void remove(VotoComentario voto) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(voto);
        tx.commit();
    }

    public void create(VotoComentario voto) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(voto);
        tx.commit();
    }

    public List<VotoComentario> getVotosComentario(Comentario comentario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from VotoComentario where comentario='" + comentario + "'");
        List<VotoComentario> votos = (List<VotoComentario>) q.list();
        tx.commit();
        return votos;
    }

    public List<VotoComentario> getVotosComentarioPositivos(Comentario comentario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from VotoComentario where tipo='true' and comentario='" + comentario + "'");
        List<VotoComentario> votos = (List<VotoComentario>) q.list();
        tx.commit();
        return votos;
    }

    public List<VotoComentario> getVotosPublicacionNegativos(Comentario comentario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from VotosPublicacion where tipo='false' and comentario='" + comentario + "'");
        List<VotoComentario> votos = (List<VotoComentario>) q.list();
        tx.commit();
        return votos;
    }
}
