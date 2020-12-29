package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Vinculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Profissional;
import com.example.algamoney.api.repository.VinculoRepository;
import com.example.algamoney.api.repository.ProfissionalRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired 
	private VinculoRepository vinculoRepository;

	public Vinculo salvar(Vinculo vinculo) {
		Profissional profissional = profissionalRepository.findOne(vinculo.getProfissional().getCodigo());
		if (profissional == null) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return vinculoRepository.save(vinculo);
	}
	
}
