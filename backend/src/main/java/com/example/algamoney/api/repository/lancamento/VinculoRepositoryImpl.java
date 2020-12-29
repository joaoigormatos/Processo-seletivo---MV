package com.example.algamoney.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.algamoney.api.model.Vinculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.algamoney.api.model.Estabelecimento_;
import com.example.algamoney.api.model.Vinculo_;
import com.example.algamoney.api.model.Profissional_;
import com.example.algamoney.api.repository.filter.VinculoFilter;
import com.example.algamoney.api.repository.projection.ResumoVinculo;

public class VinculoRepositoryImpl implements VinculoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Vinculo> filtrar(VinculoFilter vinculoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vinculo> criteria = builder.createQuery(Vinculo.class);
		Root<Vinculo> root = criteria.from(Vinculo.class);
		
		Predicate[] predicates = criarRestricoes(vinculoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Vinculo> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(vinculoFilter));
	}
	

	@Override
	public Page<ResumoVinculo> resumir(VinculoFilter vinculoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoVinculo> criteria = builder.createQuery(ResumoVinculo.class);
		Root<Vinculo> root = criteria.from(Vinculo.class);
		
		criteria.select(builder.construct(ResumoVinculo.class
				, root.get(Vinculo_.estabelecimento).get(Estabelecimento_.nome)
				, root.get(Vinculo_.profissional).get(Profissional_.nome)));
		
		Predicate[] predicates = criarRestricoes(vinculoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoVinculo> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(vinculoFilter));
	}

	private Predicate[] criarRestricoes(VinculoFilter vinculoFilter, CriteriaBuilder builder,
										Root<Vinculo> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(vinculoFilter.getEstabelecimentoCodigo())) {
			predicates.add(builder.like(
					builder.lower(root.get("codigo_estabelecimento")), "%" + vinculoFilter.getEstabelecimentoCodigo().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(vinculoFilter.getProfissionalCodigo())) {
			predicates.add(builder.like(
					builder.lower(root.get("codigo_profissional")), "%" + vinculoFilter.getEstabelecimentoCodigo().toLowerCase() + "%"));
		}

		

		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(VinculoFilter vinculoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Vinculo> root = criteria.from(Vinculo.class);
		
		Predicate[] predicates = criarRestricoes(vinculoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
