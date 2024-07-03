package com.sparta.outcome.config;

import com.sparta.outcome.batch.VideoItemProcessor;
import com.sparta.outcome.entity.Statistics;
import com.sparta.outcome.entity.Video;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


    private final EntityManagerFactory entityManagerFactory;

    public SpringBatchConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job createStatisticsJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("createStatisticsJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      ItemReader<Video> reader, ItemProcessor<Video, Statistics> processor, ItemWriter<Statistics> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Video, Statistics>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Video> reader() {
        return new JpaPagingItemReaderBuilder<Video>()
                .name("videoReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT v FROM Video v")
                .build();
    }

    @Bean
    public ItemProcessor<Video, Statistics> processor() {
        return new VideoItemProcessor();
    }

    @Bean
    public ItemWriter<Statistics> writer() {
        return new JpaItemWriterBuilder<Statistics>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
