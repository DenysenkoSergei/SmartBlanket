package com.blanket.data.dao;

import com.blanket.data.entity.User;

public interface UserDao {

    public User getUserByUsername(String username);

    public User getUserByUserId(long id);

    public int addUser(User user);

    public User checkCredentials(String login, String password);

    public boolean checkAccessRights(String username, String password);

    public boolean deleteUser(long id);
}


