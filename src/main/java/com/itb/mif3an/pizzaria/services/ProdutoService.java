package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.model.Produto;


import java.util.List;

public interface ProdutoService {

    public List<Produto> findAll();
    public Produto save(Produto produto);
    public boolean delete(Long id);

}
