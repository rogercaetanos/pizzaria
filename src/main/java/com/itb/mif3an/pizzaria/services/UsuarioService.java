package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.model.*;

public interface UsuarioService {

    public Usuario findByUsername(String username);
    public Papel savePapel(Papel papel);
    public void addPapelToUsuario(Usuario usuario, String nomePapel);
    Usuario saveFuncionario(Funcionario funcionario);
    Usuario saveCliente(Cliente cliente);
    Usuario saveAdmin(Admin admin);

}
