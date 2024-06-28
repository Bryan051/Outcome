package com.sparta.outcome.controller;

import com.sparta.outcome.entity.User;
import com.sparta.outcome.security.UserDetailsImpl;
import com.sparta.outcome.service.UserService;
import com.sparta.outcome.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
public class VideoController {

    private final UserService userService;
    private final VideoService videoService;

    @PostMapping("/{id}")
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(user);
    }

}
