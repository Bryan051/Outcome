package com.sparta.outcome.service;

import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoView;
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

    @Transactional
    public int playVideo(User userId, Video vidId) {
        Optional<VideoView> videoViewOptional = videoViewRepository.findByUserIdAndVidId(userId, vidId);
        if (videoViewOptional.isPresent()) {
            VideoView videoView = videoViewOptional.get();
            // 기존에 조회한 적 있는 경우: 마지막 시청 시점에서 재생
            return videoView.getLast_played();
        } else {
            // 처음 조회하는 경우: 처음부터 재생
            Video v = videoRepository.findById(vidId.getVidId()).orElseThrow();
            v.setViewCount(v.getViewCount() + 1); // 조회수 증가
            videoRepository.save(v);

            VideoView videoView = new VideoView();
            videoView.setUserId(userId);
            videoView.setVidId(vidId);
            videoView.setLast_played(0);
            videoViewRepository.save(videoView);

            return 0; // 처음부터 재생
        }
    }

    @Transactional
    public void stopVideo(User userId, Video vidId, int currentWatchTime) {
        VideoView videoView = videoViewRepository.findByUserIdAndVidId(userId, vidId)
                .orElseThrow(() -> new IllegalArgumentException("Video watch information not found"));

        // 현재까지 재생한 시점 저장
        videoView.setLast_played(currentWatchTime);
        videoViewRepository.save(videoView);
    }




}
