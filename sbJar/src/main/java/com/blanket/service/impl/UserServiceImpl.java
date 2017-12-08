package com.blanket.service.impl;

import com.blanket.data.dao.UserDao;
import com.blanket.data.entity.User;
import com.blanket.service.SecurityService;
import com.blanket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityService securityService;

    public boolean accountWithUsernameExists(String username) {
        return userDao.getUserByUsername(username) != null;
    }

    public User getUserById(long id) {
        return userDao.getUserByUserId(id);
    }

    public int addUser(User user) {
        String encrypted = securityService.encryptPassword(user.getPassword());
        user.setPassword(encrypted);
        return userDao.addUser(user);
    }

    public boolean deleteUserById(long id) {
        return false;
    }

    public String checkUsernameAndPassword(String login, String password) {
        String encrypted = securityService.encryptPassword(password);
        User curUser = userDao.checkCredentials(login, encrypted);
        return curUser == null ? "Denied" : new StringBuilder()
                                            .append(login)
                                            .append("\n")
                                            .append(encrypted)
                                            .toString();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
