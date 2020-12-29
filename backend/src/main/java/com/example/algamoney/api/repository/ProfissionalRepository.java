package com.example.algamoney.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	 Page<Profissional> findByNomeContaining(String nome, Pageable pageable);
	
}
