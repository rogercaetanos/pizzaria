package com.itb.mif3an.pizzaria.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoUsuario", discriminatorType = DiscriminatorType.STRING)
@EnableJpaAuditing
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipoUsuario")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Funcionario.class, name = "Funcionario"),
        @JsonSubTypes.Type(value = Cliente.class, name = "Cliente"),
        @JsonSubTypes.Type(value = Admin.class, name = "Admin")
})
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, length = 100)
    private String nome;
    @Column(nullable = true, length = 20)
    private String cpf;
    @Column(nullable = false, length = 45)
    private String username;
    @Column(nullable = false, length = 250)
    private String password;
    @Column(nullable = false, length = 45, insertable = false, updatable = false)
    private String tipoUsuario;
    @Column(nullable = true, length = 100)
    private String logradouro;
    @Column(nullable = true, length = 15)
    private String cep;
    @Column(nullable = true, length = 100)
    private String bairro;
    @Column(nullable = true, length = 2)
    private String uf;
    @Column(nullable = false)
    private boolean codStatus;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Telefone> telefones = new ArrayList<Telefone>();

    //  EAGER : Referente ao tipo de consulta : "Imediato"
    //  LAZY  : Referente ao tipo de consulta : "Preguiçoso"
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_papeis",
               joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "papel_id", referencedColumnName = "id")
              )
    private Collection<Papel> papeis;


    // Atributos de apoio

    @Transient
    @JsonIgnore
    private String mensagemErro = "";
    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Collection<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(Collection<Papel> papeis) {
        this.papeis = papeis;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return isValid == usuario.isValid && Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean validarUsuario() {

        return isValid;
    }
}
