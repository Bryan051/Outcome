package com.sparta.outcome.domain.write;

import com.sparta.outcome.domain.VideoStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VideoStatsWriteRepository extends JpaRepository<VideoStats,Long> {
}
