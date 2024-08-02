package com.itb.mif3an.pizzaria.model;

public class Categoria {

    private Long id;
    private String nome;
    private String descricao;
    private boolean codStatus;

    // Atributos de apoio

    private String mensagemErro = "";
    private boolean isValid = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public boolean validarCategoria() {
        if(nome == null || nome.isEmpty()){
            mensagemErro += "O nome da categoria é obrigatório:";
            isValid = false;
        }
        return isValid;
    }

}
