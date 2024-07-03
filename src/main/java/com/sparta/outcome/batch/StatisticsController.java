package com.sparta.outcome.batch;

import com.sparta.outcome.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/top5/views")
    public List<Statistics> getTop5VideosByViewCount(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return statisticsService.getTop5VideosByViewCount(start, end);
    }

    @GetMapping("/top5/playtime")
    public List<Statistics> getTop5VideosByPlayTime(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return statisticsService.getTop5VideosByPlayTime(start, end);
    }
}
