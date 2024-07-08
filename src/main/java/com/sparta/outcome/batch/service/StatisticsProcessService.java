//package com.sparta.outcome.batch.service;
//
//import com.sparta.outcome.dto.DailyStatsRequestDto;
//import com.sparta.outcome.entity.Ad;
//import com.sparta.outcome.entity.DailyStats;
//import com.sparta.outcome.entity.Statistics;
//import com.sparta.outcome.entity.Video;
//import com.sparta.outcome.repository.AdRepository;
//import com.sparta.outcome.repository.DailyStatsRepository;
//import com.sparta.outcome.repository.StatisticsRepository;
//import com.sparta.outcome.repository.VideoRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class StatisticsProcessService {
//
//    private final DailyStatsRepository dailyStatsRepository;
//    private final VideoRepository videoRepository;
//    private final AdRepository adRepository;
//
//    @Transactional
//    public void setDailyStats(DailyStatsRequestDto dailyStatsRequestDto){
//
//        Optional<Video> optionalVideo = videoRepository.findById(dailyStatsRequestDto.getVidId());
//        // 해당 비디오가 있다면,
//        if (optionalVideo.isPresent()) {
//            Video video = optionalVideo.get();
//            // 비디오에 해당하는 날짜의 데이터가 있는지 조회.
//            Optional<DailyStats> optionalDailyStats =
//                    dailyStatsRepository.findByDateAndVideo(dailyStatsRequestDto.getDate(), video);
//
//            // 해당 날자에 데이터 없는경우에만
//            if (optionalDailyStats.isEmpty()) {
//                // 새로운 데이터 생성
//                DailyStats dailyStats = new DailyStats();
//                dailyStats.setDate(dailyStatsRequestDto.getDate());
//                dailyStats.setVideo(video);
//                // 누적조회수 video.getViewCount() - 나머지데이터 findTotalDailyViewCountByVideo
//                Optional<Integer> dailyViewSum = dailyStatsRepository.findTotalDailyViewCountByVideo(video);
//                int dayView = video.getViewCount() - dailyViewSum;
//                dailyStats.setDailyViewCount();
//                dailyStats.setDailyAdViewCount();
//                dailyStatsRepository.save(dailyStats);
//            }
//        }
//
//    }
//
////    public List<Statistics> getTop5VideosByViewCount(LocalDate startDate, LocalDate endDate) {
////
////        Statistics statistics = new Statistics();
////        return statisticsRepository.findTop5ByDateBetweenOrderByViewCountDesc(startDate, endDate);
////    }
////
////    public List<Statistics> getTop5VideosByPlayTime(LocalDate startDate, LocalDate endDate) {
////        return statisticsRepository.findTop5ByDateBetweenOrderByPlayTimeDesc(startDate, endDate);
////    }
//}
