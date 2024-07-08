//package com.sparta.outcome.batch.config;
//
//import com.sparta.outcome.batch.StatisticsProcessor;
//import com.sparta.outcome.entity.Statistics;
//import com.sparta.outcome.entity.Video;
//import jakarta.persistence.EntityManagerFactory;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
//import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class SpringBatchConfig {
//
//
//    private final EntityManagerFactory entityManagerFactory;
//    private final StatisticsProcessor statisticsProcessor;
//
//    @Bean
//    public Job createStatisticsJob(JobRepository jobRepository, Step step1) {
//        return new JobBuilder("createStatisticsJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(step1)
//                .build();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//                      ItemReader<Video> reader, ItemProcessor<Video, Statistics> processor, ItemWriter<Statistics> writer) {
//        return new StepBuilder("step1", jobRepository)
//                .<Video, Statistics>chunk(10, transactionManager)
//                .reader(reader)
//                .processor(statisticsProcessor)
//                .writer(writer)
//                .build();
//    }
//
//    @Bean
//    public JpaPagingItemReader<Video> reader() {
//        return new JpaPagingItemReaderBuilder<Video>()
//                .name("videoReader")
//                .entityManagerFactory(entityManagerFactory)
//                .queryString("SELECT v FROM Video v")
//                .pageSize(10)
//                .build();
//    }
//
////    @Bean
////    public ItemProcessor<Video, Statistics> processor() {
////
////        return video ->{
////            return new Statistics(LocalDate.now(),video.getVidId(), video.getViewCount(), (long) video.getVidLength() * video.getViewCount());
////        };
////    }
//
//    @Bean
//    public JpaItemWriter<Statistics> writer() {
//        JpaItemWriter<Statistics> jpaItemWriter = new JpaItemWriter<>();
//        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
//        return jpaItemWriter;
//    }
//}
