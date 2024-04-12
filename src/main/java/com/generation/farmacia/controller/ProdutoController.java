package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	public ProdutoRepository produtoRepository;


	@Autowired
	public CategoriaRepository categoriaRepository;

	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {

		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}

	@GetMapping("/{id}")

	public ResponseEntity<Optional<Produto>> getById(@PathVariable Long id) {

		Optional<Produto> ProdutoOp = produtoRepository.findById(id);
		if (ProdutoOp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(ProdutoOp);
	}

	
	@GetMapping("/valor/{valor}")
	public ResponseEntity<List<Produto>> getAll(@PathVariable BigDecimal valor) {

		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByValorContainingIgnoreCase(valor));
	}

	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody @Valid Produto produto) {
		if(categoriaRepository.existsById(produto.getCategoria().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {

		if(produtoRepository.existsById(produto.getId())) {
			if(categoriaRepository.existsById(produto.getCategoria().getId())) {
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATEGORIA NÃO EXISTE");
		}
		
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PRODUTO NÃO EXISTE");
		
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> c = produtoRepository.findById(id);
		if (c.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto NÃO EXISTE");
		}
		produtoRepository.deleteById(id);
	}

}
