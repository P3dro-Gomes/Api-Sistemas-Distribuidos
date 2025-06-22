package com.example.repository;
import com.example.models.PermissaoUsuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PermissaoRepository implements PanacheRepository<PermissaoUsuario> {

    public boolean hasPermissao(String usuario, String permissao) {
        return find("usuario = ?1 and permissao = ?2", usuario, permissao).count() > 0;
    }
}