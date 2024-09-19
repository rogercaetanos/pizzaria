package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.model.Papel;
import com.itb.mif3an.pizzaria.model.Usuario;

public interface UsuarioService {

    public Usuario findByUsername(String username);
    public Papel savePapel(Papel papel);
    void addPapelToUsuario(Usuario usuario, String nomePapel);

}
