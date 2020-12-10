package com.generation.livecode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.livecode.model.LiveCode;
import com.generation.livecode.repository.LiveCodeRepository;

@RestController
@RequestMapping("/live")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class LiveCodeController {

	@Autowired
	private LiveCodeRepository repository;
	
	@GetMapping
	public ResponseEntity<List<LiveCode>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LiveCode> getById(@PathVariable long id){
		/* Optional<LiveCode> live = repository.findById(id);
		
		if(live.isPresent())
			return ResponseEntity.ok(live.get());
		
		return ResponseEntity.badRequest().build();*/		
		
		return repository.findById(id).map(batata -> ResponseEntity.ok(batata))
				.orElse(ResponseEntity.badRequest().build());
	}
	
	/*Chamando o metho (query method)*/
	@GetMapping("/nome/{titulo}")
	public ResponseEntity<List<LiveCode>> getById(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	/*Chamando o metho (query anotation)*/
	@GetMapping("/qtd/{valor}")
	public ResponseEntity<List<LiveCode>> getByIdValor(@PathVariable int valor){
		return ResponseEntity.ok(repository.maiorQue(valor));
	}
	
}
