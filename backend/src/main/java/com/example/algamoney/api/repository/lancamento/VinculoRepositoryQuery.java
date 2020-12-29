package com.example.algamoney.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Vinculo;
import com.example.algamoney.api.repository.filter.VinculoFilter;
import com.example.algamoney.api.repository.projection.ResumoVinculo;

public interface VinculoRepositoryQuery {

	public Page<Vinculo> filtrar(VinculoFilter vinculoFilter, Pageable pageable);
	public Page<ResumoVinculo> resumir(VinculoFilter vinculoFilter, Pageable pageable);
	
}
