package com.example.service;


import com.example.models.Despesa;
import com.example.models.Orcamento;
import com.example.repository.DespesaRepository;
import com.example.repository.OrcamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@ApplicationScoped
public class DespesaService {

    @Inject
    OrcamentoRepository orcamentoRepository;

    @Inject
    DespesaRepository despesaRepository;

    @Transactional
    public Despesa criarDespesa(Despesa despesa) {
        Orcamento orcamento = orcamentoRepository.findByArea(despesa.getArea());

        if (orcamento == null) {
            throw new RuntimeException("Orçamento não encontrado para a área: " + despesa.getArea());
        }

        if (orcamento.getSaldoDisponivel().compareTo(despesa.getValor()) >= 0) {
            despesa.setAprovada(true);

            BigDecimal novoSaldo = orcamento.getSaldoDisponivel().subtract(despesa.getValor());
            orcamento.setSaldoDisponivel(novoSaldo);

            orcamentoRepository.persist(orcamento);
        } else {
            despesa.setAprovada(false);
        }

        despesaRepository.persist(despesa);
        return despesa;
    }
}