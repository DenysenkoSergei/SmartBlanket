package com.blanket.data.dao;

import com.blanket.data.entity.Blanket;

import java.util.Collection;

public interface BlanketDao {

    public Blanket getBlanketById(int id);

    public Blanket getBlanketByKey(String key);

    public Collection<Blanket> getBlanketByUserId(int id);

    public boolean addBlanketOwner(Integer userId, String serialKey);

}
