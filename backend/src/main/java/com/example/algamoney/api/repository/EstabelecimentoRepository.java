package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Estabelecimento;
import com.example.algamoney.api.model.Profissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

	 Page<Estabelecimento> findByNomeContaining(String nome, Pageable pageable);
	
}
