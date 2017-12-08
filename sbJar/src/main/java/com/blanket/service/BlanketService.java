package com.blanket.service;

import com.blanket.data.entity.Blanket;
import com.blanket.data.entity.BlanketStatus;

import java.util.Collection;

public interface BlanketService {


    public Blanket getBlanketById(int id);

    public Blanket getBlanketByKey(String key);

    public int persistBlanketStatus(BlanketStatus blanket);

    public Collection<Blanket> getUserBlankets(int userId);

    public boolean addBlanketOwner(Integer userId, String serialKey);
}
