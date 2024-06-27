package com.sparta.outcome.entity;

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
@Entity(name = "video")
@Table(name = "video")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vid_id")
    private Long vidId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "vid_name")
    private String vidName;

    @Column(name = "vid_content")
    private String vidContent;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "ad_time")
    private LocalDate adTime;

    // getters and setters
}

