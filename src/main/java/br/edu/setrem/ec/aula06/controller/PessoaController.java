package br.edu.setrem.ec.aula06.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.setrem.ec.aula06.entity.Pessoa;
import br.edu.setrem.ec.aula06.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> list = repository.findAll();

		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Integer id) {
		Optional<Pessoa> pessoa = repository.findById(id);

		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public void save(@RequestBody Pessoa pessoa) {
		repository.save(pessoa);
	}

	@PutMapping
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
		Optional<Pessoa> objeto = repository.findById(pessoa.getId());

		if (objeto.isPresent()) {
			Pessoa pessoaSalva = objeto.get();
			BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
			repository.save(pessoaSalva);

			return ResponseEntity.ok(pessoaSalva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> delete(@PathVariable Integer id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
