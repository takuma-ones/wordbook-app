package com.example.demo.batch.tasklet;

import com.example.demo.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetTasklet implements Tasklet {

    private final WordRepository wordRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("------------------ Reset Tasklet Executed -----------------");
        wordRepository.resetStatus();
        return RepeatStatus.FINISHED;
    }



}
