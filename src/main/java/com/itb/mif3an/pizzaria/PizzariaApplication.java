package com.itb.mif3an.pizzaria;

import com.itb.mif3an.pizzaria.model.Papel;
import com.itb.mif3an.pizzaria.model.Produto;
import com.itb.mif3an.pizzaria.repository.PapelRepository;
import com.itb.mif3an.pizzaria.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzariaApplication {

	public static void main(String[] args) {

		SpringApplication.run(PizzariaApplication.class, args);

		Produto p = new Produto();
		// p.precoCompra = -45.00;  // Agora o acesso é privado
		// System.out.println("Preço de compra " + p.precoCompra);

		//p.setPrecoCompra(-56.00);
		//System.out.println("Preço de compra " + p.getPrecoCompra());

		p.setNome("Pizza de Calabreza tamanho G");
		p.setPrecoCompra(-56.00);

		if(!p.validarProduto()) {
			System.out.println(p.getMensagemErro());
		}

	}

	@Bean
	CommandLineRunner run(UsuarioService usuarioService, PapelRepository papelRepository){
		return args -> {
			//usuarioService.save(new Professor(null,"Rogerio","Caetano Santos", "nilcc@gmail.com", "1234", new ArrayList<>()));
			if(papelRepository.findAll().size() == 0) {
				usuarioService.savePapel(new Papel(null, "ROLE_FUNCIONARIO"));
				usuarioService.savePapel(new Papel(null, "ROLE_CLIENTE"));
				usuarioService.savePapel(new Papel(null, "ROLE_ADMIN"));
			}else {
				System.out.println("Papeis já criados no banco de dados!");
			}
		};
	}

}
