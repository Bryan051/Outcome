//package com.sparta.outcome.controller;
//
//import com.sparta.outcome.dto.BatchVideoRequestDto;
//import com.sparta.outcome.entity.Video;
//import com.sparta.outcome.repository.VideoRepository;
//import com.sparta.outcome.service.BatchVideoService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/batch")
//public class BatchVideoController {
//
//    public final BatchVideoService batchVideoService;
//    private final VideoRepository videoRepository;
//
//    // 배치 및 정산
//    @PostMapping("/countVideoViews")
//    public String countVideoViewsExcludingUser(@RequestBody BatchVideoRequestDto batchVideoRequestDto) {
//        return batchVideoService.countAndCalculateVideoViews(batchVideoRequestDto);
//    }
//    @GetMapping("/sumVideoViewDurations")
//    public Long sumVideoViewDurationsExcludingUserAndDate( @RequestBody BatchVideoRequestDto batchVideoRequestDto) {
//
//        Video video = videoRepository.findById(batchVideoRequestDto.getVidId())
//                .orElseThrow(() -> new RuntimeException("Video not found"));
//
//        return batchVideoService.sumVideoViewDurationsExcludingUserAndDate(video, batchVideoRequestDto.getDate());
//    }
//
//}
