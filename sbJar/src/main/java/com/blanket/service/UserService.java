package com.blanket.service;

import com.blanket.data.entity.User;

public interface UserService {

    public boolean accountWithUsernameExists(String username);

    public User getUserById(long id);

    public int addUser(User user);

    public boolean deleteUserById(long id);

    public String checkUsernameAndPassword(String login, String password);

    public User getUserByUsername(String username);
}
