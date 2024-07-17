package com.sparta.outcome.domain.write;

import com.sparta.outcome.domain.Ad;
import com.sparta.outcome.domain.Video;
import com.sparta.outcome.domain.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface VideoAdWriteRepository extends JpaRepository<VideoAd, Long> {
    List<VideoAd> findVideoAdByVideo(Video video);

    List<VideoAd> findVideoAdsByVideoAndAd(Video video, Ad ad);
}