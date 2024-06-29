package com.sparta.outcome.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoRequestDto {

    @NotBlank
    private Long userId;

    @NotBlank
    private Long vidId;
    private int last_played;


}
