package com.sparta.outcome.repository;

import com.sparta.outcome.entity.Ad;
import com.sparta.outcome.entity.AdView;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoAdRepository extends JpaRepository<VideoAd, Long> {
    List<VideoAd> findVideoAdByVideo(Video video);

    List<VideoAd> findVideoAdsByVideoAndAd(Video video, Ad ad);
}