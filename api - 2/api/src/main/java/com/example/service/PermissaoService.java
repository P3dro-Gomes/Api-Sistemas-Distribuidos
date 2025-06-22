package com.example.service;
import com.example.models.PermissaoUsuario;
import com.example.repository.PermissaoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PermissaoService {

    @Inject
    PermissaoRepository permissaoRepository;

    @Transactional
    public PermissaoUsuario salvarPermissao(PermissaoUsuario permissao) {
        permissaoRepository.persist(permissao);
        return permissao;
    }
    public boolean verificarPermissao(String usuario, String permissao) {
        return permissaoRepository.hasPermissao(usuario, permissao);
    }
}
