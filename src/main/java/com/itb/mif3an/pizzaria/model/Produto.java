package com.itb.mif3an.pizzaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produtos")
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = true, length = 45)
    private String tipo;

    @Column(nullable = true, length = 250)
    private String descricao;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoCompra;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoVenda;
    @Column(nullable = true)
    private int quantidadeEstoque;
    private boolean codStatus;


    // @ManyToOne :  Muitos para UM
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;


    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();

    // Atributos de apoio
    @Transient
    private String mensagemErro = "";
    @Transient
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
