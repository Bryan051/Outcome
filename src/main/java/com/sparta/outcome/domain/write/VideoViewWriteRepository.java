package com.sparta.outcome.domain.write;

import com.sparta.outcome.domain.User;
import com.sparta.outcome.domain.Video;
import com.sparta.outcome.domain.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface VideoViewWriteRepository extends JpaRepository<VideoView,Long> {


    List<VideoView> findTop2ByUserIdAndVidIdOrderByIdDesc(User user, Video video);

//    List<VideoView> findTop2ByUserIdAndVidIdOrderByIdDesc(int user, int video);

    VideoView findTopByUserIdAndVidIdOrderByIdDesc(User user, Video video);
}
