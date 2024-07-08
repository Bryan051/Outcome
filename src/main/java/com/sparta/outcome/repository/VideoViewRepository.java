package com.sparta.outcome.repository;

import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VideoViewRepository extends JpaRepository<VideoView,Long> {
    List<VideoView> findByUserIdAndVidId(User userId, Video vidId);

    List<VideoView> findTop2ByUserIdAndVidIdOrderByIdDesc(User user, Video video);

    // batch.videostats..video_view
    // 해당 VideoView 데이터 중 해당 video를 등록한 userId를 제외한 기록만, 날짜별로 선택해서 카운트
    // 돌아가는거 확인. video_stats 의 video_view 로 배치.
    @Query("SELECT COUNT(v) FROM VideoView v WHERE v.userId <> :#{#video.userId} AND v.vidId = :video AND v.createdAt = :date")
    Long countVideoViewsExcludingUserAndDate(@Param("video") Video video, @Param("date") LocalDate date);

    // batch.videostats..play_time





}
