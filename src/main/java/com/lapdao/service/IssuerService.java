package com.lapdao.service;

import java.util.Optional;

import com.lapdao.model.Issuer;

public interface IssuerService extends AbstractService<Issuer, Long> {

	Optional<Issuer> findById(Long id);
	
	Optional<Issuer> findByIssuerCode(String code);
	
	Optional<Issuer> findByName(String name);
	
	
	default String createKey(Long id) {
		return String.join("-", String.valueOf(id));
	}

}
