package com.sparta.outcome.batch;

import com.sparta.outcome.entity.Statistics;
import com.sparta.outcome.entity.Video;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class VideoItemProcessor implements ItemProcessor<Video, Statistics> {

    @Override
    public Statistics process(Video video) throws Exception {
        Statistics stats = new Statistics();
        stats.setDate(LocalDate.now());
        stats.setVidId(video.getVidId());
        stats.setPlayTime((long) video.getVidLength() * video.getViewCount());

        return stats;
    }
}