package com.sparta.outcome.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BatchVideoRequestDto {

    @NotBlank
    private Long vidId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate date;


}
