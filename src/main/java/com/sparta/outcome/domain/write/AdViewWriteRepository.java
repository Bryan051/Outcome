package com.sparta.outcome.domain.write;

import com.sparta.outcome.domain.AdView;
import com.sparta.outcome.domain.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface AdViewWriteRepository extends JpaRepository<AdView,Long> {

    // 일 광고 뷰 조회
    @Query("SELECT COUNT(a) FROM AdView a WHERE a.videoAd IN :videoAds AND a.createdAt = :date")
    long countByVideoAdsAndDate(@Param("videoAds") List<VideoAd> videoAds, @Param("date") LocalDate date);


}

