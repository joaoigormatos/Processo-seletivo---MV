package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Estabelecimento;
import com.example.algamoney.api.repository.EstabelecimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoService {
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	public Estabelecimento atualizar(Long codigo, Estabelecimento estabelecimento) {
		Estabelecimento estabelecimentoSalva = buscarPessoaPeloCodigo(codigo);
		
		BeanUtils.copyProperties(estabelecimento, estabelecimentoSalva, "codigo");
		return estabelecimentoRepository.save(estabelecimentoSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Estabelecimento estabelecimentoSalva = buscarPessoaPeloCodigo(codigo);

		estabelecimentoRepository.save(estabelecimentoSalva);
	}
	
	public Estabelecimento buscarPessoaPeloCodigo(Long codigo) {
		 Estabelecimento estabelecimentoSalva = estabelecimentoRepository.findOne(codigo);
		if (estabelecimentoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return estabelecimentoSalva;
	}
	
}
