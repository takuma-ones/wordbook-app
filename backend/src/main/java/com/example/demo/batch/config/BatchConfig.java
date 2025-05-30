package com.example.demo.batch.config;

import com.example.demo.batch.chunk.CsvItemProcessor;
import com.example.demo.batch.chunk.CsvItemReader;
import com.example.demo.batch.chunk.CsvItemWriter;
import com.example.demo.batch.tasklet.ResetTasklet;
import com.example.demo.domain.entity.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ResetTasklet resetTasklet;
    private final CsvItemReader csvItemReader;
    private final CsvItemProcessor csvItemProcessor;
    private final CsvItemWriter csvItemWriter;

    @Bean
    Job resetTaskletJob() {
        return new JobBuilder("resetTaskletJob", jobRepository)
                .start(resetTaskletStep()).build();
    }

    @Bean
    Step resetTaskletStep() {
        return new StepBuilder("resetTaskletStep", jobRepository)
                .tasklet(resetTasklet, transactionManager)
                .build();
    }

    @Bean
    Job csvChunkJob() {
        return new JobBuilder("csvChunkJob", jobRepository)
                .start(csvChunkStep()).build();
    }

    @Bean
    Step csvChunkStep() {
        return new StepBuilder("csvChunkStep", jobRepository)
                .<Word, Word>chunk(10, transactionManager)
                .reader(csvItemReader)
                .processor(csvItemProcessor)
                .writer(csvItemWriter)
                .build();
    }



}
