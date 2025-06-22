package com.example.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PermissaoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;    // Ex: "pedro"
    private String permissao;  // Ex: "ADICIONAR_ORCAMENTO"

    public PermissaoUsuario() {
    }

    public PermissaoUsuario(String usuario, String permissao) {
        this.usuario = usuario;
        this.permissao = permissao;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
}
