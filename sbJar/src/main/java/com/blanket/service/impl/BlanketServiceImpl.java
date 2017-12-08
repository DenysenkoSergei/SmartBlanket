package com.blanket.service.impl;

import com.blanket.data.dao.BlanketDao;
import com.blanket.data.dao.BlanketStatusDao;
import com.blanket.data.entity.Blanket;
import com.blanket.data.entity.BlanketCommand;
import com.blanket.data.entity.BlanketStatus;
import com.blanket.service.BlanketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

public class BlanketServiceImpl implements BlanketService {

    @Autowired
    private BlanketDao blanketDao;

    @Autowired
    private BlanketStatusDao blanketStatusDao;

    public Blanket getBlanketById(int id) {
        return blanketDao.getBlanketById(id);
    }

    public Blanket getBlanketByKey(String key) {
        return blanketDao.getBlanketByKey(key);
    }

    public int persistBlanketStatus(BlanketStatus blanket) {
        return blanketStatusDao.persistBlanketStatus(blanket);
    }

    public Collection<Blanket> getUserBlankets(int userId) {
        return blanketDao.getBlanketByUserId(userId);
    }

    @Override
    public boolean addBlanketOwner(Integer userId, String serialKey) {
        return blanketDao.addBlanketOwner(userId, serialKey);
    }
}
