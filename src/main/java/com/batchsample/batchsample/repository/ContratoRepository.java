package com.batchsample.batchsample.repository;

import com.batchsample.batchsample.domain.Contrato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends CrudRepository<Contrato, Contrato> {

    List<Contrato> getByDiaVencimento(int diaAtual);

}