package com.blanket.service;

public interface SecurityService {

    public String encryptPassword(String password);

    public boolean checkAccessRights(String username, String password);
}
