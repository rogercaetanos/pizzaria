package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.model.*;

import java.util.List;

public interface UsuarioService {

    public Usuario findByUsername(String username);
    public Papel savePapel(Papel papel);
    public void addPapelToUsuario(Usuario usuario, String nomePapel);
    public Usuario saveFuncionario(Funcionario funcionario);
    public Usuario saveCliente(Cliente cliente);
    public Usuario saveAdmin(Admin admin);
    public List<Usuario> findAll();
    public Usuario findById(Long id);


}
