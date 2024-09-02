package com.itb.mif3an.pizzaria.services;

import com.itb.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.mif3an.pizzaria.exceptions.NotFound;
import com.itb.mif3an.pizzaria.model.Produto;
import com.itb.mif3an.pizzaria.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ProdutoServiceImpl implements ProdutoService {

  //  @Autowired // Injeção de dependencia
    private final ProdutoRepository produtoRepository;
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override  // Sobrescrita do método
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    @Transactional
    public Produto save(Produto produto) {
        if(!produto.validarProduto()){
            throw new BadRequest(produto.getMensagemErro());
        }
        return produtoRepository.save(produto);
    }

    @Override
    public boolean delete(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new NotFound("Produto não encontrado com o id " + id);
        }
        produtoRepository.deleteById(id);
        return true;
    }
}
