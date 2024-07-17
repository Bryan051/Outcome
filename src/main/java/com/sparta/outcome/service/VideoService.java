package com.sparta.outcome.service;

import com.sparta.outcome.domain.User;
import com.sparta.outcome.domain.Video;
import com.sparta.outcome.domain.VideoView;
import com.sparta.outcome.domain.write.VideoViewWriteRepository;
import com.sparta.outcome.dto.VideoRequestDto;
import com.sparta.outcome.domain.read.VideoAdReadRepository;
import com.sparta.outcome.domain.read.VideoReadRepository;
import com.sparta.outcome.domain.read.VideoViewReadRepository;
import com.sparta.outcome.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoReadRepository videoReadRepository;
    private final VideoViewReadRepository videoViewReadRepository;
    private final VideoAdReadRepository videoAdReadRepository;
    private final VideoViewWriteRepository videoViewWriteRepository;
    private final VideoAdService videoAdService;

    public void playVideo(UserDetailsImpl userDetails, VideoRequestDto videoRequestDto) {
        Optional<Video> videoOptional = videoReadRepository.findById(videoRequestDto.getVidId());
//        Optional<User> userOptional = userRepository.findById(videoRequestDto.getUserId());
        User user = userDetails.getUser();

        // 비디오가 존재하지 않으면
        if (videoOptional.isEmpty()) {
            throw new RuntimeException("Video not found with id " + videoRequestDto.getVidId());
        }
        Video video = videoOptional.get();

//        List<VideoView> videoViews = videoViewRepository.findByUserIdAndVidId(user, video);
//        // videoview 에 기록이 없을때 = 초기값 설정.
//        if (videoViews.isEmpty()) {
//            // 비디오 뷰가 없는 경우 새로운 비디오 뷰 생성
//            VideoView newVideoView = new VideoView();
//            newVideoView.setUserId(user);
//            newVideoView.setVidId(video);
//            newVideoView.setCreatedAt(LocalDate.now());
//            newVideoView.setLast_played(0);
//            videoViewRepository.save(newVideoView);
//        }
        // 기존 시청기록 있어도 0으로 생성 후 pause 시에 시청구간 update
        VideoView newVideoView = new VideoView();
        newVideoView.setUserId(user);
        newVideoView.setVidId(video);
        newVideoView.setCreatedAt(LocalDate.now());
        newVideoView.setLast_played(0);
        videoViewWriteRepository.save(newVideoView);

    }

    // lastPlayed 를 서비스 로직에서 처리해서 받아와야 하는데 서비스 단을 개발하지 않으므로
    // 동영상 일시정지는 없다고 가정하고 보거나 끄는 것만 가능.
    public void pauseVideo(UserDetailsImpl userDetails, VideoRequestDto videoRequestDto) {
        Optional<Video> videoOptional = videoReadRepository.findById(videoRequestDto.getVidId());
        User user = userDetails.getUser();

        // 내가 동영상 등록한 것이라고 해도 시청기록 용으로 생성해주고 배치시에 제외
        // if (!user.getUserId().equals(videoRequestDto.getUserId()))

        if (videoOptional.isEmpty()) {
            throw new RuntimeException("Wrong request with Video id " + videoRequestDto.getVidId());
        }
        Video video = videoOptional.get();

//        List<VideoView> videoViews = videoViewRepository.findByUserIdAndVidId(user, video);
        // 가장 최신의 두 VideoView를 가져옴
        List<VideoView> videoViews = videoViewReadRepository.findTop2ByUserIdAndVidIdOrderByIdDesc(user, video);

        if (videoViews.isEmpty()) {
            // 새로운 비디오 뷰 생성을 play 에서 항상 해 놓으므로 비디오 뷰가 없는 경우는 정상적인 비디오정지가 아님
            throw new RuntimeException(" Cannot pause the video that hasn't been played. ");
        }
        else {

            // 이전의 last_played 값을 설정.
            // 이전 재생 시점 기준으로 duration을 구해 총 재생시간 top5에 이용.
            int previousLastPlayed = videoViews.size() > 1 ? videoViews.get(1).getLast_played() : 0;
            int duration = 0;
            if (videoRequestDto.getLast_played() < previousLastPlayed){
                duration = video.getVidLength() - previousLastPlayed + videoRequestDto.getLast_played();
            }else{
                duration = videoRequestDto.getLast_played() - previousLastPlayed;
            }
            // 시청기록 갱신, 해당 videoId에 해당하는 가장 최근에 생긴 videoview 를 pk 값으로 가져온다.
            Optional<VideoView> latestVideoView = videoViews.stream()
                    .max(Comparator.comparing(VideoView::getId));

            int finalDuration = duration;
            latestVideoView.ifPresent(videoView -> {
                // Request 로 받아 온 재생시점을 저장.
                videoView.setLast_played(videoRequestDto.getLast_played());
                // 현 재생시점 - 직전 재생시점 = 동영상 본 길이 += (누적합) 총 재생시간.
                videoView.setDuration(finalDuration);
                videoView.setCreatedAt(LocalDate.now());
                videoViewWriteRepository.save(videoView);
            });

        }

        // videoRequestDto.getLast_played() 보다 작으면 adView 생성
        videoAdService.createAdViewsIfNecessary(videoRequestDto, video,user);

    }

}
