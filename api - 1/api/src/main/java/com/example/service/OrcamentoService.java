package com.example.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.models.Orcamento;
import com.example.repository.OrcamentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped

public class OrcamentoService {

    @Inject
    OrcamentoRepository orcamentoRepository;

    public List<Orcamento> listarTodos() {
        return orcamentoRepository.listAll();
    }

    public Orcamento buscarPorArea(String area) {
        Orcamento orcamento = orcamentoRepository.findByArea(area);
        if (orcamento == null) {
            throw new RuntimeException("Orçamento não encontrado para a área: " + area);
        }
        return orcamento;
    }

    @Transactional
    public Orcamento criarOrcamento(Orcamento orcamento) {
        orcamentoRepository.persist(orcamento);
        return orcamento;
    }

    @Transactional
    public Orcamento atualizarSaldo(String area, BigDecimal novoSaldo) {
        Orcamento orcamento = buscarPorArea(area);
        orcamento.setSaldoDisponivel(novoSaldo);
        orcamentoRepository.persist(orcamento);
        return orcamento;
    }

    @Transactional
    public Orcamento adicionarSaldo(String area, BigDecimal valor) {
        Orcamento orcamento = buscarPorArea(area);
        orcamento.setSaldoDisponivel(orcamento.getSaldoDisponivel().add(valor));
        orcamentoRepository.persist(orcamento);
        return orcamento;
    }

    @Transactional
    public void removerOrcamento(String area) {
        Orcamento orcamento = buscarPorArea(area);
        orcamentoRepository.delete(orcamento);
    }
}
