package com.batchsample.batchsample.config;

import com.batchsample.batchsample.domain.Contrato;
import com.batchsample.batchsample.repository.ContratoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import java.time.LocalDate;

@Slf4j
@Configuration
public class StepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ContratoRepository contratoRepository;

    private static final LocalDate DATA_ATUAL = LocalDate.now();

    @Bean
    public Step createStep(final ItemWriter<Contrato> registerWriterBatch,
                           final ItemProcessor<Contrato, Contrato> registerProcessorBatch,
                           final TaskExecutor myExecutor) {

        return stepBuilderFactory.get("contratoHandlerStep")
                .<Contrato, Contrato>chunk(5)
                .reader(new ListItemReader<>(contratoRepository.getByDiaVencimento(DATA_ATUAL.getDayOfMonth())))
                .processor(registerProcessorBatch)
                .writer(registerWriterBatch)
                .taskExecutor(myExecutor)
                .build();
    }

}
