//package com.sparta.outcome.batch.job;
//
//import com.sparta.outcome.batch.service.StatisticsProcessService;
//import com.sparta.outcome.dto.DailyStatsRequestDto;
//import com.sparta.outcome.entity.Statistics;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/statistics")
//public class StatisticsController {
//    private final StatisticsProcessService statisticsProcessService;
//
//    @GetMapping("/dailystats")
//    public ResponseEntity<String> setDailyStats(@RequestBody DailyStatsRequestDto dailyStatsRequestDto){
//        statisticsProcessService.setDailyStats(dailyStatsRequestDto);
//        return ResponseEntity.ok("일 조회수, 광고 통계 완료");
//    }
//
//    @GetMapping("/Revenue")
//    public ResponseEntity<String> setDailyStats(){
//        return ResponseEntity.ok("정산완료");
//    }
//
//}
//
//
////    private StatisticsService statisticsService;
////
////    @GetMapping("/top5/views")
////    public List<Statistics> getTop5VideosByViewCount(@RequestParam String startDate, @RequestParam String endDate) {
////        LocalDate start = LocalDate.parse(startDate);
////        LocalDate end = LocalDate.parse(endDate);
////        return statisticsService.getTop5VideosByViewCount(start, end);
////    }
////
////    @GetMapping("/top5/playtime")
////    public List<Statistics> getTop5VideosByPlayTime(@RequestParam String startDate, @RequestParam String endDate) {
////        LocalDate start = LocalDate.parse(startDate);
////        LocalDate end = LocalDate.parse(endDate);
////        return statisticsService.getTop5VideosByPlayTime(start, end);
////    }
