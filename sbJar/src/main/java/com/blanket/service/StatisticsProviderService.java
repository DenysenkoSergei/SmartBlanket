package com.blanket.service;

import com.blanket.service.utility.CurrentStatistics;
import java.util.Collection;

public interface StatisticsProviderService {

    public CurrentStatistics getCurrentStatics(int blanketId);

    public Collection<CurrentStatistics> getAllTimeStatics(int blanketId);

    public Collection<CurrentStatistics> getOverallDailyStatistics(int blanketId);
}
