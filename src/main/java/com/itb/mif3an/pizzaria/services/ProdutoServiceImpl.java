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
        produto.setCodStatus(true);
        if(!produto.validarProduto()){
            throw new BadRequest(produto.getMensagemErro());
        }
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new NotFound("Produto não encontrado com o id " + id);
        }
        produtoRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Produto update(Produto produto, Long id) {
        try {
            if(!produto.validarProduto()){
                throw new BadRequest(produto.getMensagemErro());
            }
            Produto produtoDb = produtoRepository.findById(id).get();
            produtoDb.setNome(produto.getNome());
            produtoDb.setDescricao(produto.getDescricao());
            produtoDb.setPrecoVenda(produto.getPrecoVenda());
            produtoDb.setTipo(produto.getTipo());
            produtoDb.setPrecoCompra(produto.getPrecoCompra());
            produtoDb.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            return produtoRepository.save(produtoDb); // save: Atualiza quando o objeto já existe no banco
        }catch (Exception ex){
            throw new NotFound("Produto não encontrado com o id " + id);
        }

    }

    @Override
    public Produto findById(Long id) {
        try {
            return produtoRepository.findById(id).get();
        } catch (Exception ex){
            throw new NotFound("Produto não encontrado com o id " + id);
        }
    }

}
