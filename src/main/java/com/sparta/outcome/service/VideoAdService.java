package com.sparta.outcome.service;

import com.sparta.outcome.dto.VideoRequestDto;
import com.sparta.outcome.entity.Ad;
import com.sparta.outcome.entity.AdView;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoAd;
import com.sparta.outcome.repository.AdRepository;
import com.sparta.outcome.repository.AdViewRepository;
import com.sparta.outcome.repository.VideoAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VideoAdService {
    private final VideoAdRepository videoAdRepository;
    private final AdRepository adRepository;
    private final AdViewRepository adViewRepository;

    // 비디오 생성시 랜덤 광고 넣어주는 메소드
    @Transactional
    public void addRandomAdsToVideo(Video video, int adCount) {
        Random random = new Random();
        long adRepositoryCount = adRepository.count();
        for (int i = 0; i < adCount; i++) {
            // ad 목록에서 랜덤한 값 가져와서 videoad 안에 매핑하기
            long randomAdId = random.nextInt((int) adRepositoryCount) + 1;
            Optional<Ad> optionalAd = adRepository.findById(randomAdId);
            // 광고 순서 * 5분만큼 adposition 넣어줌
            int adPosition = 300 * (i + 1);
            optionalAd.ifPresent(ad -> {
                VideoAd videoAd = new VideoAd();
                videoAd.setVideo(video);
                videoAd.setAd(ad);
                videoAd.setAdPosition(adPosition);
                videoAdRepository.save(videoAd);
            });
        }
    }

    // 비디오 정지 시 Ad View 생성 메소드
    @Transactional
    public void createAdViewsIfNecessary(VideoRequestDto videoRequestDto, Video video) {
        List<VideoAd> videoAds = videoAdRepository.findVideoAdByVideo(video);

        for (VideoAd videoAd : videoAds) {
            if (videoAd.getAdPosition() < videoRequestDto.getLast_played()) {
                AdView adView = new AdView();
                adView.setCreatedAt(LocalDate.now());
                adView.setVideoAd(videoAd);
                adViewRepository.save(adView);
            }
        }
    }



}
