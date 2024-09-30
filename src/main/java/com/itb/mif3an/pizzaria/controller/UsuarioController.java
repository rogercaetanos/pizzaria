package com.itb.mif3an.pizzaria.controller;


import com.itb.mif3an.pizzaria.exceptions.NotFound;
import com.itb.mif3an.pizzaria.model.Admin;
import com.itb.mif3an.pizzaria.model.Cliente;
import com.itb.mif3an.pizzaria.model.Funcionario;
import com.itb.mif3an.pizzaria.model.Usuario;
import com.itb.mif3an.pizzaria.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getUsers() {

        return ResponseEntity.ok().body(usuarioService.findAll());
    }


    @PostMapping("/usuario/cliente")
    public ResponseEntity<Usuario> saveProfessor(@RequestBody Cliente cliente) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/usuario/professor").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.saveCliente(cliente));

    }

    @PostMapping("/usuario/admin")
    public ResponseEntity<Usuario> saveAluno(@RequestBody Admin admin) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/usuario/aluno").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.saveAdmin(admin));

    }

    @PostMapping("/usuario/funcionario")
    public ResponseEntity<Usuario> saveFuncionario(@RequestBody Funcionario funcionario) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/funcionario").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.saveFuncionario(funcionario));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> findUserById(@PathVariable(value = "id") Long id) {
        try{
            return ResponseEntity.ok().body(usuarioService.findById(id));
        }catch (Exception e){
            throw new NotFound("Usuário não encontrado com o id " + id);
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return new Date().toString();
    }

}
