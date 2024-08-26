package com.itb.mif3an.pizzaria.repository;

import com.itb.mif3an.pizzaria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
