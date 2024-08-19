package com.itb.mif3an.pizzaria.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false)
   private int quantidadeItem;
   @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
   private double precoUnitario;
   private boolean codStatus;

    // Atributos de apoio

    @Transient
    private String mensagemErro = "";
    @Transient
    private boolean isValid = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean validarItemPedido() {

        return isValid;
    }
}
