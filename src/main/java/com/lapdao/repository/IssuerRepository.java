package com.lapdao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.lapdao.model.Issuer;

public interface IssuerRepository extends JpaRepository<Issuer,Long>, QuerydslPredicateExecutor<Issuer> {

	Optional<Issuer> findById(Long id);
	
	Optional<Issuer> findByIssuerCode(String code);
	
	Optional<Issuer> findByName(String name);
	@Query("Select i from Issuer i")
	Optional<List<Issuer>> findAllIssuer();
	
}
