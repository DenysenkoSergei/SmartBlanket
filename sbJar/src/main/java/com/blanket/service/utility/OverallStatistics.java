package com.blanket.service.utility;

import com.blanket.data.entity.BlanketStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class OverallStatistics {

    Logger LOG = LoggerFactory.getLogger(OverallStatistics.class);

    private List<CurrentStatistics> data;

    public OverallStatistics(List<CurrentStatistics> data) {
        this.data = data;
    }

    public List<CurrentStatistics> getAverageByDays() throws JsonProcessingException {
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        List<CurrentStatistics> filtered = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();

        for (int i = 0; i < data.size(); ++i) {
            StatsCounter dailyCounter = new StatsCounter();
            dailyCounter.add(data.get(i));

            for (int j = i + 1; j < data.size(); ++j) {
                cal1.setTime(data.get(i).date);
                cal2.setTime(data.get(j).date);
                if (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                    dailyCounter.add(data.get(j));
                } else {
                    i = j - 1;
                    break;
                }
            }
            dailyCounter.calculateStats();
            dailyCounter.setAverageTemperature();
            dailyCounter.setAverageVibration();
            LOG.info("Aggregated val = {}", om.writeValueAsString(dailyCounter));
            filtered.add(dailyCounter);
        }
        LOG.info("Initial stats length = {}; aggregated length = {}", data.size(), filtered.size());
        Collections.reverse(filtered);
        return filtered;
    }

    private class StatsCounter extends CurrentStatistics {

        private int counter = 0;

        StatsCounter() {
            tempTopleft = 0;
            tempTopright = 0;
            tempBotleft = 0;
            tempBotright = 0;
            vibrTopleft = 0;
            vibrTopright = 0;
            vibrBotleft = 0;
            vibrBotright = 0;
        }

        void add(CurrentStatistics cs) {
            counter++;
            date = cs.date;
            blanketId = cs.blanketId;
            tempTopleft += cs.tempTopleft;
            tempTopright += cs.tempTopright;
            tempBotleft += cs.tempBotleft;
            tempBotright += cs.tempBotright;
            vibrTopleft += cs.vibrTopleft;
            vibrTopright += cs.vibrTopright;
            vibrBotleft += cs.vibrBotleft;
            vibrBotright += cs.vibrBotright;
        }

        void calculateStats() {
            tempTopleft /= counter;
            tempTopright /= counter;
            tempBotleft /= counter;
            tempBotright /= counter;
            vibrTopleft /= counter;
            vibrTopright /= counter;
            vibrBotleft /= counter;
            vibrBotright /= counter;
        }
    }
}
