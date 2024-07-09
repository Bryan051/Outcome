package com.sparta.outcome.controller;

import com.sparta.outcome.dto.BatchVideoRequestDto;
import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.repository.UserRepository;
import com.sparta.outcome.repository.VideoRepository;
import com.sparta.outcome.security.UserDetailsImpl;
import com.sparta.outcome.service.BatchVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchVideoController {

    public final BatchVideoService batchVideoService;
    private final VideoRepository videoRepository;

    @PostMapping("/countVideoViews")
    public String countVideoViewsExcludingUser( @RequestBody BatchVideoRequestDto batchVideoRequestDto) {

        Video video = videoRepository.findById(batchVideoRequestDto.getVidId()).
                orElseThrow(() -> new RuntimeException("Video not found"));
        // 오늘 계산값
        int dailyView = batchVideoService.countVideoViewsExcludingUser(video, batchVideoRequestDto.getDate());
        // 어제까지 총합
        int lastTotalView = video.getTotalVideoView();
        video.setTotalVideoView(dailyView + lastTotalView) ;

        // 일 정산
        double revenue = 0 ;
        if (lastTotalView < 100000) {
            int firstHalfView = 99999 - lastTotalView; // 10만까지 남은 뷰
            if (dailyView <= firstHalfView) {
                revenue = dailyView;
            }else {
                revenue += firstHalfView; // * 1원
                int secondHalfView = dailyView - firstHalfView;//10만 넘김
                revenue += secondHalfView * 1.1;
            }
        } else if (lastTotalView < 500000) {
            int firstHalfView = 499999 - lastTotalView; // 50만 미만
            if (dailyView <= firstHalfView) {
                revenue = dailyView;
            }else {
                revenue += firstHalfView * 1.1;
                int secondHalfView = dailyView - firstHalfView;//50만 넘김
                revenue += secondHalfView * 1.3;
            }
        } else if (lastTotalView < 1000000) {
            int firstHalfView = 999999 - lastTotalView; // 100만 미만
            if (dailyView <= firstHalfView) {
                revenue = dailyView;
            }else {
                revenue += firstHalfView * 1.3;
                int secondHalfView = dailyView - firstHalfView;//100만 넘김
                revenue += secondHalfView * 1.5;
            }
        } else {
                revenue = dailyView * 1.5;
        }

        return "일 조회수: " + dailyView + " / 일 정산액: " + revenue ;
    }

//    @PostMapping("/countVideoPlays")
//    public Long countVideoPlaysExcludingUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BatchVideoRequestDto batchVideoRequestDto) {
//
//        Video video = videoRepository.findById(batchVideoRequestDto.getVidId()).orElseThrow(() -> new RuntimeException("Video not found"));
//
//        return batchVideoService.countVideoViewsExcludingUser(video, batchVideoRequestDto.getDate());
//    }

    @GetMapping("/sumVideoViewDurations")
    public Long sumVideoViewDurationsExcludingUserAndDate( @RequestBody BatchVideoRequestDto batchVideoRequestDto) {

        Video video = videoRepository.findById(batchVideoRequestDto.getVidId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        return batchVideoService.sumVideoViewDurationsExcludingUserAndDate(video, batchVideoRequestDto.getDate());
    }

//    @GetMapping("/videoRevenue")
//    public Long sumVideoDailyRevenue(@RequestBody BatchVideoRequestDto batchVideoRequestDto){
//        Video video = videoRepository.findById(batchVideoRequestDto.getVidId())
//                .orElseThrow(() -> new RuntimeException("Video not found"));
//        int totalView = video.getTotalVideoView();
//        return batchVideoService.videoRevService(video,totalView);
//    }
}
