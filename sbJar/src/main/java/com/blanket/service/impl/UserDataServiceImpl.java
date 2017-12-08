package com.blanket.service.impl;

import com.blanket.data.dao.BlanketDao;
import com.blanket.data.dao.BlanketStatusDao;
import com.blanket.data.dao.UserDao;
import com.blanket.data.entity.BlanketStatus;
import com.blanket.data.entity.User;
import com.blanket.data.entity.Blanket;
import com.blanket.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class UserDataServiceImpl implements UserDataService {

    @Autowired
    UserDao userDao;

    @Autowired
    BlanketDao blanketDao;

    @Autowired
    BlanketStatusDao blanketStatusDao;


    public User getGeneralUserInfo(String username) {
        User user =  userDao.getUserByUsername(username);
        return user;
    }

    public BlanketStatus getCurrentBlanketStatus(int blanketId) {
        return blanketStatusDao.getLastStatus(blanketId);
    }
}
