package com.sparta.outcome.service;

import com.sparta.outcome.dto.BatchAdRequestDto;
import com.sparta.outcome.entity.VideoAd;
import com.sparta.outcome.repository.AdViewRepository;
import com.sparta.outcome.repository.VideoAdRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchAdService {

    public final VideoAdRepository videoAdRepository;
    private final AdViewRepository adViewRepository;

    @Transactional
    public long countAdViewsByAdIdAndDate(BatchAdRequestDto batchAdRequestDto) {
        // adId로 VideoAd 엔티티들을 찾음
        List<VideoAd> videoAds = videoAdRepository.findVideoAdsByAd_Id(batchAdRequestDto.getAdId());

        // VideoAd 엔티티들의 ID를 추출
        List<Long> videoAdIds = videoAds.stream()
                .map(VideoAd::getId)
                .collect(Collectors.toList());

        // videoAdIds와 date로 AdView 엔티티들을 카운트, 총 조회수
        return adViewRepository.countByVideoAdIdsAndDate(videoAdIds, batchAdRequestDto.getDate());
    }
}
