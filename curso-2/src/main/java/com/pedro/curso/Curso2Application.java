package com.pedro.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pedro.curso.domain.Categoria;
import com.pedro.curso.domain.Cidade;
import com.pedro.curso.domain.Cliente;
import com.pedro.curso.domain.Endereco;
import com.pedro.curso.domain.Estado;
import com.pedro.curso.domain.Pagamento;
import com.pedro.curso.domain.PagamentoComBoleto;
import com.pedro.curso.domain.PagamentoComCartao;
import com.pedro.curso.domain.Pedido;
import com.pedro.curso.domain.Produto;
import com.pedro.curso.domain.enums.EstadoPagamento;
import com.pedro.curso.domain.enums.TipoCliente;
import com.pedro.curso.repositories.CategoriaRepository;
import com.pedro.curso.repositories.CidadeRepository;
import com.pedro.curso.repositories.ClienteRepository;
import com.pedro.curso.repositories.EnderecoRepository;
import com.pedro.curso.repositories.EstadoRepository;
import com.pedro.curso.repositories.PagamentoRepository;
import com.pedro.curso.repositories.PedidoRepository;
import com.pedro.curso.repositories.ProdutoRepository;


@SpringBootApplication
public class Curso2Application implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;	
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;	
	
	public static void main(String[] args) {
		SpringApplication.run(Curso2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "MG");
		Estado est2 = new Estado(null, "SP");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "5465152645", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("4234234532", "32454325"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 2", "Jardim", "324234234", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Nelson", "40", "Apto 1", "Jardins", "34445", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//Variável auxiliar para formatar a data do pedido
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32") , cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
