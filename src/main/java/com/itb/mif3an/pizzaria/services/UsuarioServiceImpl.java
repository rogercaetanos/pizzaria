package com.itb.mif3an.pizzaria.services;


import com.itb.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.mif3an.pizzaria.model.*;
import com.itb.mif3an.pizzaria.repository.PapelRepository;
import com.itb.mif3an.pizzaria.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    public Usuario saveFuncionario(Funcionario funcionario) {
        funcionario.setCodStatus(true);
        if(!funcionario.validarUsuario()) {
            throw new BadRequest(funcionario.getMensagemErro());
        }
        Usuario usuario = usuarioRepository.findByUsername(funcionario.getUsername());
        if(usuario != null) {
            throw new BadRequest("Email já existente no banco de dados");
        }
        funcionario.setPassword(passwordEncoder.encode(funcionario.getPassword()));
        funcionario.setPapeis(new ArrayList<>());
        addPapelToUsuario(funcionario, "ROLE_FUNCIONARIO");
        return usuarioRepository.save(funcionario);
    }

    @Override
    public Usuario saveCliente(Cliente cliente) {
       cliente.setCodStatus(true);
       if(!cliente.validarUsuario()) {
           throw new BadRequest(cliente.getMensagemErro());
       }
       Usuario usuario = usuarioRepository.findByUsername(cliente.getUsername());
       if(usuario != null){
           throw new BadRequest("Email já existente no banco de dados");
       }
       cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
       cliente.setPapeis(new ArrayList<>());
       addPapelToUsuario(cliente, "ROLE_CLIENTE");
       return usuarioRepository.save(cliente);
    }

    @Override
    public Usuario saveAdmin(Admin admin) {
        admin.setCodStatus(true);
        if(!admin.validarUsuario()) {
            throw new BadRequest(admin.getMensagemErro());
        }
        Usuario usuario = usuarioRepository.findByUsername(admin.getUsername());
        if(usuario != null){
            throw new BadRequest("Email já existente no banco de dados");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setPapeis(new ArrayList<>());
        addPapelToUsuario(admin, "ROLE_ADMIN");
        return usuarioRepository.save(admin);
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
