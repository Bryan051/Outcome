package com.sparta.outcome.controller;

import com.sparta.outcome.dto.VideoRequestDto;
import com.sparta.outcome.security.UserDetailsImpl;
import com.sparta.outcome.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
public class VideoController {

    private final VideoService videoService;

    // 비디오 재생 요청 처리
    @PostMapping("/play")
    public ResponseEntity<String> playVideo(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody VideoRequestDto videoRequestDto) {
        videoService.playVideo(userDetails,videoRequestDto);
        return ResponseEntity.ok("Video playback started");
    }

    // 비디오 중단 요청 처리
    @PostMapping("/pause")
    public ResponseEntity<String> pauseVideo(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody VideoRequestDto videoRequestDto) {
        videoService.pauseVideo(userDetails,videoRequestDto);
        return ResponseEntity.ok("Video playback paused");
    }




}
