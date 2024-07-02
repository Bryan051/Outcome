package com.sparta.outcome.service;

import com.sparta.outcome.dto.VideoRequestDto;
import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoView;
import com.sparta.outcome.repository.UserRepository;
import com.sparta.outcome.repository.VideoRepository;
import com.sparta.outcome.repository.VideoViewRepository;
import com.sparta.outcome.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoViewRepository videoViewRepository;
//    private final UserRepository userRepository;

    public void playVideo(UserDetailsImpl userDetails, VideoRequestDto videoRequestDto) {
        Optional<Video> videoOptional = videoRepository.findById(videoRequestDto.getVidId());
//        Optional<User> userOptional = userRepository.findById(videoRequestDto.getUserId());
        User user = userDetails.getUser();

        // 비디오가 존재하면
        if (videoOptional.isEmpty()) {
            throw new RuntimeException("Video not found with id " + videoRequestDto.getVidId());
        }
        Video video = videoOptional.get();
//            User user = userOptional.get();
        // 시청기록 가져오기. 없으면 생성.
        VideoView videoView = videoViewRepository.findByUserIdAndVidId(user, video)
                .orElseGet(() -> new VideoView(user, video));

        // 중간 저장 기록 없으면
        if (videoView.getLast_played() == 0) {
            video.setViewCount(video.getViewCount() + 1);
            videoRepository.save(video);
        }
        videoView.setUpdatedAt(LocalDateTime.now());
        videoViewRepository.save(videoView);
    }

    // 비디오 중단 시 호출
    // lastPlayed 를 서비스 로직에서 처리해서 받아와야 하는데 서비스 단을 개발하지 않으므로
    // 동영상 일시정지는 없다고 가정하고 보거나 끄는 것만 가능.
    public void pauseVideo(UserDetailsImpl userDetails,VideoRequestDto videoRequestDto) {
        Optional<Video> videoOptional = videoRepository.findById(videoRequestDto.getVidId());
        User user = userDetails.getUser();
//        Optional<User> userOptional = userRepository.findById(videoRequestDto.getUserId());

        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
//            User user = userOptional.get();

            VideoView videoView = videoViewRepository.findByUserIdAndVidId(user, video)
                    .orElseGet(() -> new VideoView(user, video));

            // 현재까지 재생한 시점 저장
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(videoView.getUpdatedAt(), now);
            int additionalSeconds = (int) duration.getSeconds();
            // 저번 재생시점에 추가 재생(additionalSeconds)시간 더해서 재생시점 재설정.
            // 비디오 길이보다 긴 경우 0 = 다 봤다고 판단, 조회수 1 증가.
            int newLastPlayed = videoView.getLast_played() + additionalSeconds;
            if (newLastPlayed > video.getVidLength()) {
                newLastPlayed = 0;
                video.setViewCount(video.getViewCount() + 1);
            }

            videoView.setLast_played(newLastPlayed);
            videoView.setUpdatedAt(LocalDateTime.now());
            videoViewRepository.save(videoView);
        }
    }

}
