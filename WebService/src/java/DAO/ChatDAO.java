package DAO;

import POJOs.Chat;
import POJOs.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class ChatDAO {

    Session session = null;

    public void update(Chat chat) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(chat);
        tx.commit();
    }

    public void remove(Chat chat) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(chat);
        tx.commit();
    }

    public void create(Chat chat) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(chat);
        tx.commit();
    }

    public Chat getChat(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Chat where id='" + id + "'");
        Chat chat = (Chat) q.uniqueResult();
        tx.commit();
        return chat;
    }

}