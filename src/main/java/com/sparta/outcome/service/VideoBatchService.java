//package com.sparta.outcome.service;
//
//import com.sparta.outcome.entity.User;
//import com.sparta.outcome.entity.Video;
//import com.sparta.outcome.repository.VideoViewRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class VideoBatchService {
//
//    private final VideoViewRepository videoViewRepository;
//
//    // video Stats
//    @Transactional
//    public Long countVideoViewsExcludingUser(User user, Video video) {
//        return videoViewRepository.countVideoViewsExcludingUser(user, video);
//    }
//
//    public void videoStatsService() {
//    }
//
//    public void videoRevService() {
//    }
//
//}
