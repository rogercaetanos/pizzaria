package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();
    Categoria save(Categoria categoria);
}
