package com.blanket.data.dao.impl;

import com.blanket.data.LocalHibernateSessionFactory;
import com.blanket.data.dao.BlanketDao;
import com.blanket.data.entity.Blanket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class BlanketDaoImpl implements BlanketDao {
    private static final SessionFactory sessionFactory = LocalHibernateSessionFactory.getSessionFactory();
    private static final Logger LOGGER = LoggerFactory.getLogger(BlanketDaoImpl.class);

    @Override
    public Blanket getBlanketById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Blanket blanket = session.find(Blanket.class, 1);
            session.getTransaction().commit();
            return blanket;
        }
    }

    @Override
    public Blanket getBlanketByKey(String key) {
        try (Session session = sessionFactory.getCurrentSession()) {
            LOGGER.info("getBlanketKey: " + key);
            session.beginTransaction();
            List<Blanket> blankets = session.createQuery("select b from Blanket b where b.serialCode = :key", Blanket.class)
                    .setParameter("key", key)
                    .getResultList();
            session.getTransaction().commit();
            LOGGER.info("getBlanketKey: result set length " + blankets.size());
            return blankets.get(0);
        }
    }

    @Override
    public Collection<Blanket> getBlanketByUserId(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            LOGGER.info("getBlanketByUserId " + id);
            session.beginTransaction();
            List<Blanket> blankets = session.createNativeQuery("select b.* from Blanket b where b.user_id = :id", Blanket.class)
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            LOGGER.info("getBlanketByUserId (" + id + "): result set length " + blankets.size());
            return blankets;
        }
    }

    @Override
    public boolean addBlanketOwner(Integer userId, String serialKey) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            if (userId != null) {
                Long counter = session.createQuery("select count(id) from Blanket b where b.serialCode = :key and userByUserId.id is null", Long.class)
                        .setParameter("key", serialKey)
                        .getSingleResult();
                if (counter.equals(0L)) {
                    return false;
                }
            } else {
                Long counter = session.createQuery("select count(id) from Blanket b where b.serialCode = :key and userByUserId.id is not null", Long.class)
                        .setParameter("key", serialKey)
                        .getSingleResult();
                if (counter.equals(0L)) {
                    return false;
                }
            }
            session.createQuery("update Blanket b set b.userByUserId.id = :userId where b.serialCode = :key")
                    .setParameter("userId", userId)
                    .setParameter("key", serialKey)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        }
    }

}
