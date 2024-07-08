package com.sparta.outcome.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VideoAdDto {
    private Long vidId;
    private Long adId;
    private int adViewCount;

    // Getters and Setters
}