package com.sparta.outcome.loader;

import com.sparta.outcome.entity.Ad;
import com.sparta.outcome.entity.User;
import com.sparta.outcome.entity.Video;
import com.sparta.outcome.repository.AdRepository;
import com.sparta.outcome.repository.UserRepository;
import com.sparta.outcome.repository.VideoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdRepository adRepository;
//    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Random random = new Random();

        // 광고 10개 생성
        List<Ad> ads = new ArrayList<>();
        for (int i = 0; i <= 10 ; i++) {
            Ad ad = new Ad();
            ad.setUrl(String.valueOf(i+1));
            ad.setViewCount(0);
            ads.add(adRepository.save(ad));
        }

        long adCount = adRepository.count();
        if (adCount == 0) {
            throw new IllegalStateException("No ads available to assign to videos.");
        }

        for (int i = 1; i <= 10; i++) {
            // 사용자 생성
            User user = new User();
            user.setUserName("user" + i);
            user.setUserEmail("asdf"+i+"@asdf.com");
            user.setPassword(passwordEncoder.encode("password" + i));
            userRepository.save(user);

            // 각 사용자마다 10개의 비디오 생성
            for (int j = 1; j <= 10; j++) {
                Video video = new Video();
                video.setUserId(user);
                int randomlength = random.nextInt(10800) + 30 ; // 30초에서 3시간 사이의 랜덤 길이
                video.setVidLength(randomlength);
                video.setViewCount(0);
                video.setCreatedAt(LocalDateTime.now());
                videoRepository.save(video);
                // 영상마다 vid_length / 300 초 만큼 랜덤한 광고 할당
                int adCountPerVid = (randomlength) / 300;
                for (int k = 0; k < adCountPerVid; k++) {
                    // 광고 10개 랜덤으로 들어간다.
                    long randomAdId = random.nextLong(adCount) + 1;
                    Optional<Ad> randomAd = adRepository.findById(randomAdId);
                    randomAd.ifPresent(video.getAds()::add); // Optional을 해제하여 추가
                }
                videoRepository.save(video);
//
//                // 주기적으로 flush와 clear 호출하여 트랜잭션 분할
                // 페이징 문제였음
//                    entityManager.flush();
//                    entityManager.clear();
            }

        }
    }

}
