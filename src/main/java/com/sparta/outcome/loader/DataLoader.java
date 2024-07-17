package com.sparta.outcome.loader;

import com.sparta.outcome.domain.VideoView;
import com.sparta.outcome.domain.read.UserReadRepository;
import com.sparta.outcome.domain.read.VideoAdReadRepository;
import com.sparta.outcome.domain.read.VideoReadRepository;
import com.sparta.outcome.domain.read.VideoViewReadRepository;
import com.sparta.outcome.domain.write.*;
import com.sparta.outcome.service.VideoAdService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VideoWriteRepository videoWriteRepository;
    private final UserWriteRepository userWriteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdWriteRepository adWriteRepository;
    private final VideoAdService videoAdService;
    private final VideoViewReadRepository videoViewReadRepository;
    private final VideoViewWriteRepository videoViewWriteRepository;
    private final VideoAdReadRepository videoAdReadRepository;
    private final AdViewWriteRepository adViewWriteRepository;
    private final VideoReadRepository videoReadRepository;
    private final UserReadRepository userReadRepository;
    private final VideoAdWriteRepository videoAdWriteRepository;

    @PersistenceContext(unitName = "writeEntityManagerFactory")
    private EntityManager writeEntityManager;

    private final Map<String, VideoView> videoViewCache = new HashMap<>();
    private final int CACHE_SIZE_LIMIT = 10000;

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void run(String... args) throws Exception {
//        Random random = new Random();

//        // 초기 데이터 생성
//        // 광고 10개 생성
//        List<Ad> ads = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            Ad ad = new Ad();
//            ad.setCreatedAt(LocalDate.now());
//            ads.add(adWriteRepository.save(ad));
//        }
//
//        long adCount = adWriteRepository.count();
//        if (adCount == 0) {
//            throw new IllegalStateException("No ads available to assign to videos.");
//        }
//
//        for (int i = 1; i <= 5; i++) {
//            // 사용자 생성
//            User user = new User();
//            user.setUserName("user" + i);
//            user.setUserEmail("asdf" + i + "@asdf.com");
//            user.setPassword(passwordEncoder.encode("password" + i));
//            userWriteRepository.save(user);
//
//            // 각 사용자마다 5개의 비디오 생성
//            for (int j = 1; j <= 5; j++) {
//                Video video = new Video();
//                video.setUserId(user);
//                int randomlength = random.nextInt(3600) + 30; // 30초에서 1시간 사이의 랜덤 길이
//                video.setVidLength(randomlength);
//                video.setViewCount(0);
//                video.setCreatedAt(LocalDate.now());
//                video.setUpdatedAt(LocalDate.now());
//                videoWriteRepository.save(video);
//
//                // 영상마다 vid_length / 300 초 만큼 랜덤한 광고 할당
//                int adCountPerVid = (randomlength) / 300;
//                videoAdService.addRandomAdsToVideo(video, adCountPerVid);
//            }
//        }


//        // 더미 데이터 수동으로 더하기  폐기.
//        // vid_id ( user 5명, video 각 5명) 25 중 랜덤 선택, userid(5명) 랜덤 선택,
//        long videoCount = videoWriteRepository.count();
//        long userCount = userWriteRepository.count();
//
//        // 데이터가 없으면 예외 발생
//        if (videoCount == 0 || userCount == 0) {
//            throw new IllegalStateException("No videos or users available.");
//        }
//
//        // 비디오와 사용자 목록을 미리 로드
//        List<Video> videos = videoWriteRepository.findAll();
//        List<User> users = userWriteRepository.findAll();
//
//        // 모든 VideoAd 데이터를 미리 로드
//        Map<Long, List<VideoAd>> videoAdsCache = new HashMap<>();
//        for (Video video : videos) {
//            videoAdsCache.put(video.getVidId(), videoAdWriteRepository.findVideoAdByVideo(video));
//        }
//
//        // 모든 사용자와 비디오 조합의 캐시 키를 생성
//        List<String> cacheKeys = new ArrayList<>();
//        for (User user : users) {
//            for (Video video : videos) {
//                String cacheKey = user.getUserId() + "_" + video.getVidId();
//                cacheKeys.add(cacheKey);
//                videoViewCache.put(cacheKey, null); // 초기화
//            }
//        }
//
//        int createViews = 100000;
//        int batchSize = createViews / cacheKeys.size(); // 각 조합당 처리할 작업 수
//
//        for (String cacheKey : cacheKeys) {
//            for (int i = 0; i < batchSize; i++) {
//                createVideoViews(random, cacheKey, videos, users, videoAdsCache);
//            }
//        }
//    }
//
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void createVideoViews(Random random, String cacheKey, List<Video> videos,
//                                 List<User> users, Map<Long, List<VideoAd>> videoAdsCache) {
//
//        // 해당하는 캐시 키의 조합 한번에 위 batchSize for문이 반복되고 사이즈는 원하는 createView 갯수/조합갯수
//        String[] ids = cacheKey.split("_");
//        Long userId = Long.parseLong(ids[0]);
//        Long videoId = Long.parseLong(ids[1]);
//
//        User user = users.stream().filter(u -> u.getUserId().equals(userId)).findFirst().orElseThrow();
//        Video video = videos.stream().filter(v -> v.getVidId().equals(videoId)).findFirst().orElseThrow();
//
//        VideoView latestVideoView = videoViewCache.get(cacheKey);
//
//
//        int vidLength = video.getVidLength();
//        int requestLastPlayed = random.nextInt(vidLength - 30) + 30;
//        int previousLastPlayed = latestVideoView != null ? latestVideoView.getLast_played() : 0;
//
//        int duration = requestLastPlayed < previousLastPlayed
//                ? video.getVidLength() - previousLastPlayed + requestLastPlayed
//                : requestLastPlayed - previousLastPlayed;
//
//        VideoView newVideoView = new VideoView();
//        newVideoView.setUserId(user);
//        newVideoView.setVidId(video);
//        newVideoView.setCreatedAt(LocalDate.now());
//        newVideoView.setDuration(duration);
//        newVideoView.setLast_played(requestLastPlayed);
//        videoViewWriteRepository.save(newVideoView);
//        // 캐시에 새로운 VideoView 객체를 추가 (덮어쓰기)
//        videoViewCache.put(cacheKey, newVideoView);
//
//        List<VideoAd> videoAds = videoAdsCache.get(video.getVidId());
//        int adCount = 0;
//        int maxAdsPerVideoView = 3;
//
//        for (VideoAd videoAd : videoAds) {
//            if (videoAd.getAdPosition() > previousLastPlayed && videoAd.getAdPosition() <= requestLastPlayed) {
//                if (adCount >= maxAdsPerVideoView) {
//                    break;
//                }
//                AdView adView = new AdView();
//                adView.setCreatedAt(LocalDate.now());
//                adView.setVideoAd(videoAd);
//                adViewWriteRepository.save(adView);
//                adCount++;
//            }
//        }
//        writeEntityManager.flush();
//    }
//
//
//}
    }
}

//            Video video = videos.get(random.nextInt(videos.size()));
//            User user = users.get(random.nextInt(users.size()));
//
//            String cacheKey = user.getUserId() + "_" + video.getVidId();
//            VideoView latestVideoView = videoViewCache.get(cacheKey);

// 배제때림
//            if (videoViews.isEmpty()) {
//                VideoView newVideoView = new VideoView();
//                newVideoView.setUserId(user);
//                newVideoView.setVidId(video);
//                newVideoView.setCreatedAt(LocalDate.now());
//                newVideoView.setLast_played(0);
//                videoViewWriteRepository.save(newVideoView);
//            }


//            long randomVidId = random.nextLong(videoCount) + 1;
//            Optional<Video> optionalVideo = videoWriteRepository.findById(randomVidId);
//            if (optionalVideo.isEmpty()) {
//                i--; // batchSize를 채우기 위해 건너뛴 경우 반복 카운터를 줄임
//                continue;
//            }
//            Video video = optionalVideo.get();
//
//            long randomUserId = random.nextLong(userCount) + 1;
//            Optional<User> optionalUser = userWriteRepository.findById(randomUserId);
//            if (optionalUser.isEmpty()) {
//                i--; // batchSize를 채우기 위해 건너뛴 경우 반복 카운터를 줄임
//                continue;
//            }
//            User user = optionalUser.get();