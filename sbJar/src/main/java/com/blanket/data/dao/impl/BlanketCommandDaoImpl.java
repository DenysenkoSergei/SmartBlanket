package com.blanket.data.dao.impl;

import com.blanket.data.LocalHibernateSessionFactory;
import com.blanket.data.dao.BlanketCommandDao;
import com.blanket.data.entity.BlanketCommand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BlanketCommandDaoImpl implements BlanketCommandDao {
    private SessionFactory sessionFactory = LocalHibernateSessionFactory.getSessionFactory();

    @Override
    public BlanketCommand getBlanketCommandByBlanketId(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<BlanketCommand> commands = session.createQuery("select bc from BlanketCommand bc where bc.blanketId = :blanketId", BlanketCommand.class)
                    .setParameter("blanketId", id)
                    .getResultList();
            session.getTransaction().commit();
            return commands.size() == 0 ? null : commands.get(0);
        }
    }

    @Override
    public int deleteBlanketCommandByBlanketId(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from BlanketCommand bc where bc.blanketId = :blanketId")
                    .setParameter("blanketId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return result;
        }
    }
}
