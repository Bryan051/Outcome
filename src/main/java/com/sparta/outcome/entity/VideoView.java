package com.sparta.outcome.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public VideoView(User user, Video video) {
        this.userId = user;
        this.vidId = video;
        this.last_played = 0; // 처음 생성 시 현재 재생 시간은 0으로 설정
    }

}
