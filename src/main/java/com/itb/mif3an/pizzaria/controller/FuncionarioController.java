package com.itb.mif3an.pizzaria.controller;


import com.itb.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.mif3an.pizzaria.exceptions.NotFound;
import com.itb.mif3an.pizzaria.model.Categoria;
import com.itb.mif3an.pizzaria.model.Produto;
import com.itb.mif3an.pizzaria.services.CategoriaService;
import com.itb.mif3an.pizzaria.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

// @Controller                                       -> Exclusivo para sistemas WEB
@RestController                                      // -> Exclusivo para APIS
@RequestMapping("/api/v1/funcionario")
public class FuncionarioController {

    public final CategoriaService categoriaService;
    public final ProdutoService produtoService;

    public FuncionarioController(CategoriaService categoriaService, ProdutoService produtoService) {
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @PostMapping("/categoria")
    @Transactional
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/categoria").toUriString());
        return ResponseEntity.created(uri).body(categoriaService.save(categoria));

    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> listarTodosCategorias() {

        return ResponseEntity.ok().body(categoriaService.findAll());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> listarCategoriaPorId(@PathVariable(value = "id") String id) {
        try {
            Long idLong = Long.parseLong(id);
            return ResponseEntity.ok().body(categoriaService.findById(idLong));
        }catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido.Por favor, forneça um valor inteiro, como 42.");
        }
    }

    @PostMapping("/produto")
    @Transactional
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/produto").toUriString());
        return ResponseEntity.created(uri).body(produtoService.save(produto));

    }

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> listarTodosProdutos() {

        return ResponseEntity.ok().body(produtoService.findAll());
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> listarProdutoPorId(@PathVariable(value = "id") String id) {
        try {
            Long idLong = Long.parseLong(id);
            return ResponseEntity.ok().body(produtoService.findById(idLong));
        }catch (NumberFormatException ex) {
           throw new BadRequest("'" + id + "' não é um número inteiro válido.Por favor, forneça um valor inteiro, como 42.");
        }
    }

}
