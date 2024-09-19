package com.itb.mif3an.pizzaria.services;


import com.itb.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.mif3an.pizzaria.model.Papel;
import com.itb.mif3an.pizzaria.model.Usuario;
import com.itb.mif3an.pizzaria.repository.PapelRepository;
import com.itb.mif3an.pizzaria.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PapelRepository papelRepository) {
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Papel savePapel(Papel papel) {
        papel.setCodStatusPapel(true);
        if(!papel.validarPapel()) {
            throw new BadRequest(papel.getMensagemErro());
        }
        return papelRepository.save(papel);
    }
    @Override
    public void addPapelToUsuario(Usuario usuario, String nomePapel) {
       Papel papel = papelRepository.findByNomePapel(nomePapel);
       usuario.getPapeis().add(papel);
    }
}
