package com.blanket.service.utility;

import com.blanket.data.entity.BlanketCommand;
import com.blanket.data.entity.BlanketStatus;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;


public class CurrentStatistics {

    public double averageTemp;
    public double averageVibr;

    public int blanketId;
    public Integer tempTopleft;
    public Integer tempTopright;
    public Integer tempBotleft;
    public Integer tempBotright;
    public Integer vibrTopleft;
    public Integer vibrTopright;
    public Integer vibrBotleft;
    public Integer vibrBotright;
    public Date date;

    public CurrentStatistics() {}

    public CurrentStatistics(BlanketCommand blanketCommand) {
        blanketId = blanketCommand.getId();
        tempTopleft = blanketCommand.getTempTopleft();
        tempTopright = blanketCommand.getTempTopright();
        tempBotleft = blanketCommand.getTempBotleft();
        tempBotright = blanketCommand.getTempBotright();
        vibrTopleft = blanketCommand.getVibrTopleft();
        vibrTopright = blanketCommand.getVibrTopright();
        vibrBotleft = blanketCommand.getVibrBotleft();
        vibrBotright = blanketCommand.getVibrBotright();
        setAverageTemperature();
        setAverageVibration();
    }

    public CurrentStatistics(BlanketStatus blanketStatus) {
        blanketId = blanketStatus.getId();
        tempTopleft = blanketStatus.getTempTopleft();
        tempTopright = blanketStatus.getTempTopright();
        tempBotleft = blanketStatus.getTempBotleft();
        tempBotright = blanketStatus.getTempBotright();
        vibrTopleft = blanketStatus.getVibrTopleft();
        vibrTopright = blanketStatus.getVibrTopright();
        vibrBotleft = blanketStatus.getVibrBotleft();
        vibrBotright = blanketStatus.getVibrBotright();
        date = blanketStatus.getDatetime();
        setAverageTemperature();
        setAverageVibration();
    }

    protected double setAverageTemperature() {
        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance();
        decimalFormat.applyPattern("###.#");
        String dbl = decimalFormat.format((tempTopleft + tempBotleft + tempTopright + tempBotright)/(double)4);
        averageTemp = Double.valueOf(dbl);
        return averageTemp;
    }

    protected double setAverageVibration() {
        String pattern = "###.#";
        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance();
        decimalFormat.applyPattern(pattern);
        String dbl = decimalFormat.format((vibrTopleft + vibrBotleft + vibrTopright + vibrBotright)/(double)4);
        averageVibr = Double.valueOf(dbl);
        return averageVibr;
    }
}
