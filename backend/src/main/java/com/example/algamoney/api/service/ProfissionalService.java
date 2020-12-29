package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Profissional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.repository.ProfissionalRepository;

@Service
public class ProfissionalService {
	
	@Autowired
	private ProfissionalRepository profissionalRepository;

	public Profissional atualizar(Long codigo, Profissional profissional) {
		Profissional profissionalSalva = buscarPessoaPeloCodigo(codigo);
		
		BeanUtils.copyProperties(profissional, profissionalSalva, "codigo");
		return profissionalRepository.save(profissionalSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Profissional profissionalSalva = buscarPessoaPeloCodigo(codigo);

		profissionalRepository.save(profissionalSalva);
	}
	
	public Profissional buscarPessoaPeloCodigo(Long codigo) {
		Profissional profissionalSalva = profissionalRepository.findOne(codigo);
		if (profissionalSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return profissionalSalva;
	}
	
}
