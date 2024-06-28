package com.sparta.outcome.repository;

import com.sparta.outcome.entity.DailyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyStatsRepository extends JpaRepository<DailyStats,Long> {
}
