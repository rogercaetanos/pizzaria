package com.itb.mif3an.pizzaria.model;

public class Produto {

    // Encapsulamento : proteger meus atributos do acesso desordenado, pode estar ligado
    //                 às questões de validação

    // Para obter o encapsulamento temos que seguir alguns passos:

    // 1º Passo : Trabalhar os modificadores de acesso :
    // Temos três:
    // public    ->  Acesso livre para todas as classes
    // private   -> Acesso permitido apenas dentro da classe
    // protected -> Acesso permitido apenas para as classes filhas (Herança)

    // Enfim, deixar os atributos private ou protected

    // 2º Passo: Criar métodos de acesso aos atributos, uma prática muito utilizada
    //           é criar os métodos SETTER´S E GETTER´S
    //   SET -> atribuir a informação
    //   GET -> recuperar a informação

    private Long id;
    private String nome;
    private String tipo;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int quantidadeEstoque;
    private boolean codStatus;

    // Atributos de apoio
    private String mensagemErro = "";
    private boolean isValid = true;

    // void: "mudo", ou seja, o método não tem retorno

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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

    public boolean validarProduto() {
        if(nome == null || nome.isEmpty()){
            mensagemErro += "O nome do produto é obrigatório:";
            isValid = false;
        }
        if(precoCompra < 0){
            precoCompra = 0;
            mensagemErro += "O preço do produto deve ser maior que zero:";
            isValid = false;
        }
        return isValid;
    }


}
