package com.sparta.outcome.repository;

import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoViewRepository extends JpaRepository<VideoView,Long> {
    List<VideoView> findByUserIdAndVidId(User userId, Video vidId);

    // video 통계시 해당 video id 에 대한 videoview를 등록한 :userId 제외한 기록만 선택해서 카운트
    @Query("SELECT COUNT(v) FROM VideoView v WHERE v.userId <> :user AND v.vidId = :video")
    Long countVideoViewsExcludingUser(@Param("user") User user, @Param("video") Video video);

//    @Query(value = "SELECT COUNT(*) FROM video_view vw " +
//            "JOIN video v ON vw.video_id = v.video_id " +
//            "WHERE vw.video_id = :videoId " +
//            "AND vw.date = :date " +
//            "AND v.user_id <> vw.user_id", nativeQuery = true)
//    Integer countByVideoIdAndDateAndDifferentUser(@Param("videoId") Long videoId, @Param("date") LocalDate date);

}
