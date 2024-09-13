package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.mif3an.pizzaria.exceptions.NotFound;
import com.itb.mif3an.pizzaria.model.Categoria;
import com.itb.mif3an.pizzaria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        categoria.setCodStatus(true);
        if(!categoria.validarCategoria()) {
            throw new BadRequest(categoria.getMensagemErro());
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria findById(Long id) {
        try {
            return categoriaRepository.findById(id).get();
        } catch (Exception ex){
            throw new NotFound("Categoria não encontrada com o id " + id);
        }
    }
}
