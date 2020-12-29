package com.example.algamoney.api.resource;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Estabelecimento;
import com.example.algamoney.api.repository.EstabelecimentoRepository;
import com.example.algamoney.api.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoResource {

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTABELECIMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Estabelecimento> criar(@Valid @RequestBody Estabelecimento estabelecimento, HttpServletResponse response) {
		Estabelecimento estabelecimentoSalva = estabelecimentoRepository.save(estabelecimento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estabelecimentoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoSalva);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTABELECIMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Estabelecimento> buscarPeloCodigo(@PathVariable Long codigo) {
		Estabelecimento estabelecimento = estabelecimentoRepository.findOne(codigo);
		return estabelecimento != null ? ResponseEntity.ok(estabelecimento) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		System.out.println(codigo);
		estabelecimentoRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Estabelecimento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Estabelecimento estabelecimento) {
		Estabelecimento estabelecimentoSalva = estabelecimentoService.atualizar(codigo, estabelecimento);
		return ResponseEntity.ok(estabelecimentoSalva);
	}
	

	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public Page<Estabelecimento> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return estabelecimentoRepository.findByNomeContaining(nome, pageable);
	}

}
