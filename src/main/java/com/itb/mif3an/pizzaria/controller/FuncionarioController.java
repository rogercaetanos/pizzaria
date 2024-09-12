package com.itb.mif3an.pizzaria.controller;


import com.itb.mif3an.pizzaria.model.Categoria;
import com.itb.mif3an.pizzaria.model.Produto;
import com.itb.mif3an.pizzaria.services.CategoriaService;
import com.itb.mif3an.pizzaria.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

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

    @PostMapping("/produto")
    @Transactional
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/produto").toUriString());
        return ResponseEntity.created(uri).body(produtoService.save(produto));

    }



}
