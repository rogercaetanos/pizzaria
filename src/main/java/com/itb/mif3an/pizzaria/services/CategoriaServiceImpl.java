package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.mif3an.pizzaria.model.Categoria;
import com.itb.mif3an.pizzaria.repository.CategoriaRepository;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
    @Override
    public Categoria save(Categoria categoria) {
        if(!categoria.validarCategoria()) {
            throw new BadRequest(categoria.getMensagemErro());
        }
        return categoriaRepository.save(categoria);
    }
}
