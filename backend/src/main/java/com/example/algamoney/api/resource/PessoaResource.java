package com.example.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.algamoney.api.model.Profissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.repository.ProfissionalRepository;
import com.example.algamoney.api.service.ProfissionalService;

@RestController
@RequestMapping("/funcionarios")
public class PessoaResource {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Profissional> criar(@Valid @RequestBody Profissional profissional, HttpServletResponse response) {
		Profissional profissionalSalva = profissionalRepository.save(profissional);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, profissionalSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(profissionalSalva);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Profissional> buscarPeloCodigo(@PathVariable Long codigo) {
		Profissional profissional = profissionalRepository.findOne(codigo);
		return profissional != null ? ResponseEntity.ok(profissional) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		System.out.println(codigo);
		profissionalRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Profissional> atualizar(@PathVariable Long codigo, @Valid @RequestBody Profissional profissional) {
		Profissional profissionalSalva = profissionalService.atualizar(codigo, profissional);
		return ResponseEntity.ok(profissionalSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		profissionalService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public Page<Profissional> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return profissionalRepository.findByNomeContaining(nome, pageable);
	}

}
