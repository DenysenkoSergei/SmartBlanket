package com.blanket.service.utility;

import com.blanket.data.entity.BlanketCommand;
import com.blanket.data.entity.BlanketStatus;

public class Utility {

    public static BlanketCommand getBlanketCommandFromStatus(BlanketStatus bs) {
        BlanketCommand bc = new BlanketCommand();
        bc.setBlanketId(bs.getId());
        bc.setTempTopleft(bs.getVibrTopleft());
        bc.setTempTopright(bs.getTempTopright());
        bc.setTempBotleft(bs.getTempBotleft());
        bc.setTempBotright(bs.getTempBotright());
        bc.setVibrTopleft(bs.getVibrTopleft());
        bc.setVibrTopright(bs.getVibrTopright());
        bc.setVibrBotleft(bs.getVibrBotleft());
        bc.setVibrBotright(bs.getVibrBotright());
        bc.setId(0);
        return bc;
    }
}
