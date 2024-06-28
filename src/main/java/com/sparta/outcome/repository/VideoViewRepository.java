package com.sparta.outcome.repository;

import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoViewRepository extends JpaRepository<VideoView,Long> {
    Optional<VideoView> findByUserIdAndVidId(User userId, Video vidId);
}
