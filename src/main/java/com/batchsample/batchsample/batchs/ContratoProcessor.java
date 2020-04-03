package com.batchsample.batchsample.batchs;

import com.batchsample.batchsample.domain.Contrato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@StepScope
@Component
public class ContratoProcessor implements ItemProcessor<Contrato, Contrato> {

    @Override
    public Contrato process (final Contrato contrato) throws Exception {
        log.info("processor: {}", contrato);

        if (isContratoInvalido(contrato)){
            return null;
        }

        return contrato;
    }

    private boolean isContratoInvalido(Contrato contrato) {
        return contrato == null;
    }

}