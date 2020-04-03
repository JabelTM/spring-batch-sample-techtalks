package com.batchsample.batchsample.batchs;

import com.batchsample.batchsample.domain.Contrato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@StepScope
@Component
public class BoletoWriter implements ItemWriter<Contrato>, InitializingBean {

    private static final String QUEBRA_DE_LINHA = "\n";

    private static final String TABULACAO = "\t";

    private BufferedOutputStream bufferedOutputStream;

    @Value("#{jobParameters[nomeArquivo]}")
    private String nomeArquivo;

    @Value("#{jobParameters[pathArquivo]}")
    private String caminhoArquivo;

    @Override
    public void write (final List<? extends Contrato> contratos) throws Exception {
        log.info("writer: {}", contratos);

        contratos.forEach(contrato -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(contrato.getId());
            stringBuilder.append(TABULACAO);
            stringBuilder.append(contrato.getNumeroContrato());
            stringBuilder.append(TABULACAO);
            stringBuilder.append(contrato.getNomeCliente());
            stringBuilder.append(TABULACAO);
            stringBuilder.append(contrato.getValorParcela());
            stringBuilder.append(QUEBRA_DE_LINHA);

            try {
                bufferedOutputStream.write(stringBuilder.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        bufferedOutputStream.flush();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        File arquivoSaida = new File(caminhoArquivo + File.separator + nomeArquivo);
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(arquivoSaida));
    }
}