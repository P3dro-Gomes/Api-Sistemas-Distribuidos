package com.example.repository;


import com.example.models.Orcamento;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrcamentoRepository implements PanacheRepository<Orcamento> {
    
    public Orcamento findByArea(String area) {
        return find("area", area).firstResult();
    }
}