package com.blanket.service.impl;


import com.blanket.data.dao.UserDao;
import com.blanket.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserDao userDao;

    public String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public boolean checkAccessRights(String username, String password) {
        return userDao.checkAccessRights(username, password);
    }
}
