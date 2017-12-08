package com.blanket.data.dao.impl;

import com.blanket.data.LocalHibernateSessionFactory;
import com.blanket.data.dao.BlanketStatusDao;
import com.blanket.data.entity.BlanketStatus;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class BlanketStatusDaoImpl implements BlanketStatusDao {
    private static final SessionFactory sessionFactory = LocalHibernateSessionFactory.getSessionFactory();
    private static final Logger LOGGER = LoggerFactory.getLogger(BlanketStatusDaoImpl.class);

    @Override
    public int persistBlanketStatus(BlanketStatus blanketStatus) {
        try (Session session = sessionFactory.getCurrentSession()) {
            LOGGER.info("persist " + new ObjectMapper().writeValueAsString(blanketStatus));
            session.beginTransaction();
            session.createNativeQuery("\n" +
                    "insert into blanket_status (blanket_id, temp_topleft, temp_topright, temp_botleft, temp_botright, vibr_topleft, " +
                    "vibr_topright, vibr_botleft, vibr_botright) values (:blanketId, :topLeft1, :topRight1, :botLeft1, :botRight1," +
                    ":topLeft2, :topRight2, :botLeft2, :botRight2)")
                    .setParameter("blanketId", blanketStatus.getBlanketByBlanketId().getId())
                    .setParameter("topLeft1", blanketStatus.getVibrTopleft())
                    .setParameter("topRight1", blanketStatus.getTempTopright())
                    .setParameter("botLeft1", blanketStatus.getTempBotleft())
                    .setParameter("botRight1", blanketStatus.getTempBotright())
                    .setParameter("topLeft2", blanketStatus.getVibrTopleft())
                    .setParameter("topRight2", blanketStatus.getVibrTopright())
                    .setParameter("botLeft2", blanketStatus.getVibrBotleft())
                    .setParameter("botRight2", blanketStatus.getVibrBotright())
                    .executeUpdate();
            session.getTransaction().commit();
            return 0;
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }
        return -1;
    }

    @Override
    public BlanketStatus getLastStatus(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            LOGGER.info("Last status for " + id);
            session.beginTransaction();
            Query query = session.createQuery("select bs from BlanketStatus bs where bs.blanketByBlanketId.id = :id order by bs.id desc", BlanketStatus.class);
            query.setMaxResults(1);
            query.setParameter("id", id);
            List list = query.getResultList();
            return list.isEmpty() ? null : (BlanketStatus) list.get(0);
        }
    }

    @Override
    public Collection<BlanketStatus> getAllStatuses(int blanketId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            LOGGER.info("All statuses for " + blanketId);
            session.beginTransaction();
            return session.createQuery("select bs from BlanketStatus bs where bs.blanketByBlanketId.id = :id order by bs.id desc", BlanketStatus.class)
                    .setParameter("id", blanketId)
                    .getResultList();
        }
    }

}
