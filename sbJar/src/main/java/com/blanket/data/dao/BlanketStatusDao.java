package com.blanket.data.dao;

import com.blanket.data.entity.BlanketStatus;
import java.util.Collection;

public interface BlanketStatusDao {

    public int persistBlanketStatus(BlanketStatus blanketStatus);

    public BlanketStatus getLastStatus(int id);

    public Collection<BlanketStatus> getAllStatuses(int blanketId);

}
