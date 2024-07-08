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
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    @PostMapping("/countVideoViews")
    public Long countVideoViewsExcludingUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BatchVideoRequestDto batchVideoRequestDto) {

        // 내가 로그인한 user 말고 해당 video를 등록한 userId 제외
//        User user = userRepository.findById(userDetails.getUser().getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Video video = videoRepository.findById(batchVideoRequestDto.getVidId()).orElseThrow(() -> new RuntimeException("Video not found"));

        return batchVideoService.countVideoViewsExcludingUser(video, batchVideoRequestDto.getDate());
    }

    @PostMapping("/countVideoPlays")
    public Long countVideoPlaysExcludingUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BatchVideoRequestDto batchVideoRequestDto) {

        Video video = videoRepository.findById(batchVideoRequestDto.getVidId()).orElseThrow(() -> new RuntimeException("Video not found"));

        return batchVideoService.countVideoViewsExcludingUser(video, batchVideoRequestDto.getDate());
    }

    @GetMapping("/sumVideoViewDurations")
    public Long sumVideoViewDurationsExcludingUserAndDate(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BatchVideoRequestDto batchVideoRequestDto) {

        Video video = videoRepository.findById(batchVideoRequestDto.getVidId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        return batchVideoService.sumVideoViewDurationsExcludingUserAndDate(video, batchVideoRequestDto.getDate());
    }



}
