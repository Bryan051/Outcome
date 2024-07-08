package com.sparta.outcome.service;

import com.sparta.outcome.dto.VideoRequestDto;
import com.sparta.outcome.entity.*;
import com.sparta.outcome.repository.AdRepository;
import com.sparta.outcome.repository.AdViewRepository;
import com.sparta.outcome.repository.VideoAdRepository;
import com.sparta.outcome.repository.VideoViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VideoAdService {
    private final VideoAdRepository videoAdRepository;
    private final AdRepository adRepository;
    private final AdViewRepository adViewRepository;
    private final VideoViewRepository videoViewRepository;

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
    public void createAdViewsIfNecessary(VideoRequestDto videoRequestDto, Video video,User user) {

        // 가장 최신의 두 VideoView를 가져옴
        List<VideoView> videoViews = videoViewRepository.findTop2ByUserIdAndVidIdOrderByIdDesc(user, video);

        // 이전의 last_played 값을 설정. 2개이상일수밖에없음.
        int previousLastPlayed = videoViews.size() > 1 ? videoViews.get(1).getLast_played() : 0;

        // 모든 VideoAd를 가져옴
        List<VideoAd> videoAds = videoAdRepository.findVideoAdByVideo(video);
        for (VideoAd videoAd : videoAds) {
            // VideoAd의 AdPosition이 previousLastPlayed와 last_played 사이에 있는지 확인
            if (videoAd.getAdPosition() > previousLastPlayed && videoAd.getAdPosition() <= videoRequestDto.getLast_played()) {
                AdView adView = new AdView();
                adView.setCreatedAt(LocalDate.now());
                adView.setVideoAd(videoAd);
                adViewRepository.save(adView);
            }
        }
    }

//    // AdView 가 생성되기 이전에 가장 최근 videoView 가 있다면 .getLast_played() 시간 사이에 있는 비디오만 불러서온다.
//    //  해당 videoId에 해당하는 가장 최근에 생긴 videoview 를 pk 값으로 가져온다.
//    Optional<VideoView> latestVideoView = videoViews.stream()
//            .max(Comparator.comparing(VideoView::getId));
//
//            latestVideoView.ifPresent(videoView -> {
//        // Request 로 받아 온 재생시점을 저장.
//        videoView.setLast_played(videoRequestDto.getLast_played());
//        videoView.setCreatedAt(LocalDate.now());
//


}
