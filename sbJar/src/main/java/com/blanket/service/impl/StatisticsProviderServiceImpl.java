package com.blanket.service.impl;

import com.blanket.data.dao.BlanketStatusDao;
import com.blanket.service.BlanketCommandService;
import com.blanket.service.BlanketService;
import com.blanket.service.StatisticsProviderService;
import com.blanket.service.utility.CurrentStatistics;
import com.blanket.service.utility.OverallStatistics;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsProviderServiceImpl implements StatisticsProviderService{

    @Autowired
    private BlanketService blanketService;

    @Autowired
    private BlanketCommandService blanketCommandService;

    @Autowired
    private BlanketStatusDao blanketStatusDao;

    public CurrentStatistics getCurrentStatics(int blanketId) {
        return new CurrentStatistics(blanketStatusDao.getLastStatus(blanketId));
    }

    public Collection<CurrentStatistics> getAllTimeStatics(int blanketId) {
        Collection<CurrentStatistics> stats = blanketStatusDao
                .getAllStatuses(blanketId)
                .stream()
                .map(CurrentStatistics::new)
                .collect(Collectors.toList());
        Collections.reverse((List<?>) stats);
        return stats;
    }

    public Collection<CurrentStatistics> getOverallDailyStatistics(int blanketId) {
        OverallStatistics os = new OverallStatistics(blanketStatusDao
                .getAllStatuses(blanketId).stream().map(CurrentStatistics::new).collect(Collectors.toList()));
        try {
            return os.getAverageByDays();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
