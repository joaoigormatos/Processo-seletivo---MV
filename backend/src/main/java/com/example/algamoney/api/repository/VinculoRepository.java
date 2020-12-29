package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.repository.lancamento.VinculoRepositoryQuery;

public interface VinculoRepository extends JpaRepository<Vinculo, Long>, VinculoRepositoryQuery {

}
