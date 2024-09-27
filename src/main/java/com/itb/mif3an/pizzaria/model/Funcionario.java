package com.itb.mif3an.pizzaria.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Funcionario")
public class Funcionario extends Usuario {

    @Column(nullable = true, length = 45)
    private String cargo;
    @Column(nullable = true, length = 45)
    private String matriculaFuncional;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMatriculaFuncional() {
        return matriculaFuncional;
    }

    public void setMatriculaFuncional(String matriculaFuncional) {
        this.matriculaFuncional = matriculaFuncional;
    }
}
