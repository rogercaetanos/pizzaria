package com.itb.mif3an.pizzaria.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "papeis")
public class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 45)
    private String nomePapel;
    @Column(nullable = true, length = 250)
    private String descricaoPapel;
    private boolean codStatusPapel;
    @Transient
    @JsonIgnore
    private String mensagemErro = "";
    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public Papel() {

    }

    public Papel(Long id, String nomePapel) {
        this.id = id;
        this.nomePapel = nomePapel;
        this.codStatusPapel = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(String nomePapel) {
        this.nomePapel = nomePapel;
    }

    public String getDescricaoPapel() {
        return descricaoPapel;
    }

    public void setDescricaoPapel(String descricaoPapel) {
        this.descricaoPapel = descricaoPapel;
    }

    public boolean isCodStatusPapel() {
        return codStatusPapel;
    }

    public void setCodStatusPapel(boolean codStatusPapel) {
        this.codStatusPapel = codStatusPapel;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public boolean validarPapel() {
        if(nomePapel == null || nomePapel.isEmpty()) {
            mensagemErro += "O nome do papel é obrigatório:";
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Papel papel = (Papel) o;
        return Objects.equals(id, papel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
