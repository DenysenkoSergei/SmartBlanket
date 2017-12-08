package com.blanket.service.impl;

import com.blanket.data.dao.BlanketCommandDao;
import com.blanket.data.dao.BlanketStatusDao;
import com.blanket.data.entity.BlanketCommand;
import com.blanket.service.BlanketCommandService;
import com.blanket.service.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class BlanketCommandServiceImpl implements BlanketCommandService {

    @Autowired
    private BlanketCommandDao blanketCommandDao;

    @Autowired BlanketStatusDao blanketStatusDao;

    public BlanketCommand getLastStatus(int id) {
        BlanketCommand command =  blanketCommandDao.getBlanketCommandByBlanketId(id);
        blanketCommandDao.deleteBlanketCommandByBlanketId(id);
        if (command == null) {
            command = Utility.getBlanketCommandFromStatus(blanketStatusDao.getLastStatus(id));
        }
        if (command == null) {
            command = new BlanketCommand();
            command.setId(-1);
        }
        return command;
    }

}
