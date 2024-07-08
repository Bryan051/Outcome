package com.sparta.outcome.entity;

import com.sparta.outcome.dto.VideoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "VideoView")
@Table(name = "Video_View")
public class VideoView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "vid_id", nullable = false)
    private Video vidId;

    @Column(nullable = false)
    private int last_played; // in seconds

//    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate createdAt;


    public VideoView(User user, Video video, VideoRequestDto videoRequestDto, LocalDate date) {
        this.userId = user;
        this.vidId = video;
        this.last_played = videoRequestDto.getLast_played(); // 처음 생성 시 현재 재생 시간은 0으로 설정
        this.createdAt = date;
    }

}
