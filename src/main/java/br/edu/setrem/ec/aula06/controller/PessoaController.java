package br.edu.setrem.ec.aula06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.setrem.ec.aula06.entity.Pessoa;
import br.edu.setrem.ec.aula06.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;
	
	@GetMapping
	public List<Pessoa> listar() {
		return repository.findAll();
	}
	
	@PostMapping
	public void save(@RequestBody Pessoa p) {
		repository.save(p);
	}
	
	public void save2(@RequestParam int codigo,  @RequestParam String nome) {
		Pessoa p = new Pessoa();
		p.setId(null);
	}
}
