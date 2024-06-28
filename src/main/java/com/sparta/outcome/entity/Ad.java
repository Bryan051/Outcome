package com.sparta.outcome.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Ad")
@Table(name = "Ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private int viewCount;
    // 광고 재생 카운트 올라갈 때 제일 최신의 날짜 / 정산시 사용
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    // 광고 게시한 날짜
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // video entity 의 set<ad> ads 필드에 의해 매핑된다.
    // 즉 Video 엔티티가 이 관계의 주인(Owner)이다.
    @ManyToMany(mappedBy = "ads")
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "ad")
    private List<DailyStats> dailyStats = new ArrayList<>();
}
