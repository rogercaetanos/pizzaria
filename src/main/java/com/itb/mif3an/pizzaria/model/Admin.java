package com.itb.mif3an.pizzaria.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Admin")
public class Admin extends Usuario {

    @Column(nullable = true, length = 15)
    private String matriculaAdmin;

    public String getMatriculaAdmin() {
        return matriculaAdmin;
    }

    public void setMatriculaAdmin(String matriculaAdmin) {
        this.matriculaAdmin = matriculaAdmin;
    }
}
