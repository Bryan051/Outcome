//package com.sparta.outcome.loader;
//
//import com.sparta.outcome.entity.User;
//import com.sparta.outcome.entity.Video;
//import com.sparta.outcome.repository.UserRepository;
//import com.sparta.outcome.repository.VideoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Random;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//    private final VideoRepository videoRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Random random = new Random();
//
//        for (int i = 1; i <= 10; i++) {
//            // 사용자 생성
//            User user = new User();
//            user.setUserName("user" + i);
//            user.setPassword(passwordEncoder.encode("password" + i));
//            userRepository.save(user);
//
//            // 각 사용자마다 10개의 비디오 생성
//            for (int j = 1; j <= 10; j++) {
//                Video video = new Video();
//                video.setUserId(user);
//                video.setVidLength(random.nextInt(10800) + 30); // 30초에서 3시간 사이의 랜덤 길이
//                video.setViewCount(0);
//                videoRepository.save(video);
//            }
//        }
//    }
//
//}
