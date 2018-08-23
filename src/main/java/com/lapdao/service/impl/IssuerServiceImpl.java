package com.lapdao.service.impl;

import java.util.Optional;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.lapdao.common.internal.exception.CanNotSaveToStorageException;
import com.lapdao.common.internal.exception.CommonException;
import com.lapdao.common.internal.exception.ErrorCode;
import com.lapdao.model.Issuer;
import com.lapdao.repository.IssuerRepository;
import com.lapdao.service.IssuerService;

@MessageEndpoint
public class IssuerServiceImpl extends AbstractImplService<Issuer, Long , IssuerRepository> implements IssuerService {
 
	@ServiceActivator(inputChannel = "issuer-create-service-channel", outputChannel = "issuer-create-outputTransformer-channel")
	public Issuer create(Issuer issuer) throws CommonException {

		try {
			return repo.save(issuer);

		} catch (Exception e) {

			throw new CanNotSaveToStorageException(ErrorCode.CANT_CREATE_ENTITY, e.getMessage());
		} finally {
			removeFromInvokingQueue(issuer.getInvokingQueue());
		}

	}

	@Override
	public Optional<Issuer> findById(Long id) {

		return repo.findById(id);
	}

	@Override
	public Optional<Issuer> findByIssuerCode(String code) {

		return repo.findByIssuerCode(code);
	}

	@Override
	public Optional<Issuer> findByName(String name) {

		return repo.findByName(name);
	}

}
