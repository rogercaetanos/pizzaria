package com.itb.mif3an.pizzaria.repository;

import com.itb.mif3an.pizzaria.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
