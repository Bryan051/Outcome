package com.sparta.outcome.service;

import com.sparta.outcome.dto.SignupRequestDto;
import com.sparta.outcome.domain.User;
import com.sparta.outcome.jwt.JwtUtil;
import com.sparta.outcome.domain.read.UserReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReadRepository userReadRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequestDto signupRequestDto) {
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 이름 중복 확인
        Optional<User> checkUserName = userReadRepository.findByUserName(signupRequestDto.getUserName());
        if(checkUserName.isPresent()) {
            throw new IllegalStateException("중복된 이름이 존재합니다.");
        }
        // 이메일 중복 확인
        Optional<User> checkMember = userReadRepository.findByUserEmail(signupRequestDto.getUserEmail());
        if(checkMember.isPresent()) {
            throw new IllegalStateException("중복된 이메일이 존재합니다.");
        }

        // 회원 가입
        User user = new User(signupRequestDto.getUserEmail(), signupRequestDto.getUserName(),password,signupRequestDto.isAuthority(),false);
        userReadRepository.save(user);
    }



}
