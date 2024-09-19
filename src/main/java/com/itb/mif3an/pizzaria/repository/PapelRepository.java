package com.itb.mif3an.pizzaria.repository;

import com.itb.mif3an.pizzaria.model.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {

    @Query(value = "SELECT * FROM papeis p WHERE p.nome_papel=?", nativeQuery = true)
    Papel findByNomePapel(String nomePapel);
}
