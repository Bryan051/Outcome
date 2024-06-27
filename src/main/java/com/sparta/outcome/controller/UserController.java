package com.sparta.outcome.controller;

import com.sparta.outcome.dto.request.auth.SignupRequestDto;
import com.sparta.outcome.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // Validation
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            for (final FieldError fieldError : fieldErrors) {
                log.error(fieldError.getField() + ": " + fieldError.getDefaultMessage());
                return fieldError.getDefaultMessage();
            }
        }
        userService.signup(signupRequestDto);
        return "회원가입 성공";
    }

}