package com.sparta.outcome.repository;

import com.sparta.outcome.entity.AdView;
import com.sparta.outcome.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AdViewRepository extends JpaRepository<AdView,Long> {

    // 일 광고 뷰 조회
    @Query("SELECT COUNT(a) FROM AdView a WHERE a.videoAd IN :videoAds AND a.createdAt = :date")
    long countByVideoAdsAndDate(@Param("videoAds") List<VideoAd> videoAds, @Param("date") LocalDate date);


}

