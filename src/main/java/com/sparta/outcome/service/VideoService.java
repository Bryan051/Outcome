package com.sparta.outcome.service;

import com.sparta.outcome.dto.VideoRequestDto;
import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoView;
import com.sparta.outcome.repository.UserRepository;
import com.sparta.outcome.repository.VideoRepository;
import com.sparta.outcome.repository.VideoViewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoViewRepository videoViewRepository;
    private final UserRepository userRepository;

    public void playVideo(VideoRequestDto videoRequestDto) {
        Optional<Video> videoOptional = videoRepository.findById(videoRequestDto.getVidId());
        Optional<User> userOptional = userRepository.findById(videoRequestDto.getUserId());
        // 비디오가 존재하면
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
            User user = userOptional.get();

            VideoView videoView = videoViewRepository.findByUserIdAndVidId(user,video)
                    .orElseGet(() -> new VideoView(user, video));

            // 중간 저장 기록 없으면
            if (videoView.getLast_played() == 0) {
                video.setViewCount(video.getViewCount() + 1);
                videoRepository.save(video);
            }
        }
    }

    // 비디오 중단 시 호출
    public void pauseVideo(VideoRequestDto videoRequestDto) {
        Optional<Video> videoOptional = videoRepository.findById(videoRequestDto.getVidId());
        Optional<User> userOptional = userRepository.findById(videoRequestDto.getUserId());

        if (videoOptional.isPresent() && userOptional.isPresent()) {
            Video video = videoOptional.get();
            User user = userOptional.get();

            VideoView videoView = videoViewRepository.findByUserIdAndVidId(user, video)
                    .orElseGet(() -> new VideoView(user, video));

            videoView.setLast_played(videoRequestDto.getLast_played());
            videoViewRepository.save(videoView);
        }
    }

}
