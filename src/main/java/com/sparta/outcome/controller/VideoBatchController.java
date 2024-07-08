//package com.sparta.outcome.controller;
//
//import com.sparta.outcome.entity.User;
//import com.sparta.outcome.entity.Video;
//import com.sparta.outcome.repository.UserRepository;
//import com.sparta.outcome.repository.VideoRepository;
//import com.sparta.outcome.security.UserDetailsImpl;
//import com.sparta.outcome.service.VideoBatchService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/batch")
//public class VideoBatchController {
//
//    public final VideoBatchService videoBatchService;
//    private final UserRepository userRepository;
//    private final VideoRepository videoRepository;
//
//    @GetMapping("/countVideoViews")
//    public Long countVideoViewsExcludingUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long videoId) {
//        // User와 Video를 리포지토리에서 가져온다고 가정
//        User user = userRepository.findById(userDetails.getUser().getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
//        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
//
//        return videoBatchService.countVideoViewsExcludingUser(user, video);
//    }
//
//}
