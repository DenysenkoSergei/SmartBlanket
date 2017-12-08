package com.blanket.data.dao.impl;

import com.blanket.data.LocalHibernateSessionFactory;
import com.blanket.data.dao.UserDao;
import com.blanket.data.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao {
    private static final SessionFactory sessionFactory = LocalHibernateSessionFactory.getSessionFactory();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getUserByUsername(String username) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createNamedQuery(User.GET_USER_BY_USERNAME, User.class)
                    .setParameter("username", username)
                    .getResultList();
            session.getTransaction().commit();
            return users.isEmpty() ? null : users.get(0);
        }
    }

    @Override
    public User getUserByUserId(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select u from User u where u.id = :id", User.class)
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            return Optional.ofNullable(users)
                    .map(u -> u.get(0))
                    .orElse(null);
        }
    }

    @Override
    public int addUser(User user) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(user);
            List<User> users = session
                            .createQuery("select u from User u where u.username = :username", User.class)
                            .setParameter("username", user.getUsername())
                            .getResultList();
            session.getTransaction().commit();
            return users.isEmpty() ? -1 : users.get(0).getId();
        }
    }

    @Override
    public User checkCredentials(String login, String password) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session
                    .createQuery("select u from User u where u.username = :username and u.password = :password", User.class)
                    .setParameter("username", login)
                    .setParameter("password", password)
                    .getResultList();
            LOGGER.info("Accounts with login: " + login + " and pass: " + password + " counter returned: " + users.size());
            return users.isEmpty() ? null : users.get(0);
        }
    }

    @Override
    public boolean checkAccessRights(String username, String password) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            long counted = session
                    .createQuery("select count(u.id) from User u where u.username = :username and u.password = :password", Long.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            LOGGER.info("Accounts with login: " + username + " and pass: " + password + " counter returned: " + counted);
            return counted == 1;
        }
    }

    @Override
    public boolean deleteUser(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = new User();
            user.setId((int)id);
            session.delete(id);
            return true;
        }
    }
}


