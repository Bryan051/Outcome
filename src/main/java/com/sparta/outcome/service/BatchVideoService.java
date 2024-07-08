package com.sparta.outcome.service;

import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.repository.VideoViewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BatchVideoService {

    private final VideoViewRepository videoViewRepository;

    // video Stats
    @Transactional
    public Long countVideoViewsExcludingUser(Video video, LocalDate date) {
        return videoViewRepository.countVideoViewsExcludingUserAndDate(video, date);
    }

    public void videoStatsService() {
    }

    public void videoRevService() {
    }

}
