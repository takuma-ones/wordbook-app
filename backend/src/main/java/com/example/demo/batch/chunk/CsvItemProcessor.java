package com.example.demo.batch.chunk;

import com.example.demo.domain.entity.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class CsvItemProcessor implements ItemProcessor<Word, Word> {

    @Override
    public Word process(Word item) throws Exception {
        log.info("----CSVファイルの編集を実行----", item);
        item.setStatus(false);
        item.setNextPresentation(null);
        return item;
    }
}
