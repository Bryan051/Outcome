package com.sparta.outcome.batch;

import com.sparta.outcome.entity.Statistics;
import com.sparta.outcome.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public List<Statistics> getTop5VideosByViewCount(LocalDate startDate, LocalDate endDate) {
        return statisticsRepository.findTop5ByDateBetweenOrderByViewCountDesc(startDate, endDate);
    }

    public List<Statistics> getTop5VideosByPlayTime(LocalDate startDate, LocalDate endDate) {
        return statisticsRepository.findTop5ByDateBetweenOrderByPlayTimeDesc(startDate, endDate);
    }
}
