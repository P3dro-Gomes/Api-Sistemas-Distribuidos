package com.example.repository;

import com.example.models.Despesa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DespesaRepository implements PanacheRepository<Despesa> {
}
