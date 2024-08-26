package com.itb.mif3an.pizzaria.repository;

import com.itb.mif3an.pizzaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
