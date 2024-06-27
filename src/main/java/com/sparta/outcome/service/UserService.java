package com.sparta.outcome.service;

import com.sparta.outcome.dto.request.auth.SignupRequestDto;
import com.sparta.outcome.entity.UserEntity;
import com.sparta.outcome.jwt.JwtUtil;
import com.sparta.outcome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequestDto signupRequestDto) {
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 이름 중복 확인
        Optional<UserEntity> checkUserName = userRepository.findByUserName(signupRequestDto.getUserName());
        if(checkUserName.isPresent()) {
            throw new IllegalStateException("중복된 이름이 존재합니다.");
        }
        // 이메일 중복 확인
        Optional<UserEntity> checkMember = userRepository.findByUserEmail(signupRequestDto.getUserEmail());
        if(checkMember.isPresent()) {
            throw new IllegalStateException("중복된 이메일이 존재합니다.");
        }

        // 회원 가입
        UserEntity userEntity = new UserEntity(signupRequestDto.getUserEmail(), signupRequestDto.getUserName(),password,signupRequestDto.isAuthority(),false);
        userRepository.save(userEntity);
    }

}
