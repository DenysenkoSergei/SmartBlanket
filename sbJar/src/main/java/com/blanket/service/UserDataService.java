package com.blanket.service;

import com.blanket.data.entity.BlanketStatus;
import com.blanket.data.entity.User;

public interface UserDataService {

    public User getGeneralUserInfo(String username);

    public BlanketStatus getCurrentBlanketStatus(int blanketId);
}
