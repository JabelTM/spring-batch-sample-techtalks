package com.batchsample.batchsample.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Contrato {

    @Id
    private Long id;

    private String nomeCliente;

    private String numeroContrato;

    private BigDecimal valorParcela;

    private int diaVencimento;

}