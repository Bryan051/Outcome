package com.sparta.outcome.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {


    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String userEmail;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String userName;

    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,13}$", message = "8~13,알파벳과 숫자") // 규칙
    private String password;

    private boolean authority;
    private boolean role;

}
