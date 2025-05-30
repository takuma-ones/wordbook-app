package com.example.demo.batch.chunk;

import com.example.demo.domain.entity.Word;
import com.example.demo.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
@RequiredArgsConstructor
public class CsvItemWriter implements ItemWriter<Word> {

    private final WordRepository wordRepository;

    @Override
    public void write(@NonNull Chunk<? extends Word> chunk) throws Exception {

        log.info("----登録データ={}----", chunk);
        wordRepository.saveAll(chunk);
        log.info("----CSVデータ登録完了----");

    }
}
