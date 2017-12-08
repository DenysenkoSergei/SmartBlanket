package com.blanket.data.dao;

import com.blanket.data.entity.BlanketCommand;

public interface BlanketCommandDao {

    public BlanketCommand getBlanketCommandByBlanketId(int id);

    public int deleteBlanketCommandByBlanketId(int id);
}
