package com.itb.mif3an.pizzaria.services;


import com.itb.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.mif3an.pizzaria.model.Papel;
import com.itb.mif3an.pizzaria.model.Usuario;
import com.itb.mif3an.pizzaria.repository.PapelRepository;
import com.itb.mif3an.pizzaria.repository.UsuarioRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado no banco de dados");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuario.getPapeis().forEach(papel -> {
            authorities.add(new SimpleGrantedAuthority(papel.getNomePapel()));
        });

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), authorities);
    }
}
