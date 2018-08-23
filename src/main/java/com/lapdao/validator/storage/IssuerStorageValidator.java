package com.lapdao.validator.storage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

import com.lapdao.common.internal.RequestPayload;
import com.lapdao.common.internal.exception.CommonException;
import com.lapdao.common.internal.exception.DuplicatedException;
import com.lapdao.common.internal.exception.ErrorCode;
import com.lapdao.model.Issuer;
import com.lapdao.request.IssuerRequest;
import com.lapdao.service.IssuerService;

@MessageEndpoint
public class IssuerStorageValidator extends CommonStorageVaildator {
	
	private @Autowired IssuerService issuerService;
	
	@Filter(inputChannel = "issuer-create-storageValidator-channel", outputChannel = "issuer-create-inputTransformer-channel")
	  public boolean create(RequestPayload<IssuerRequest> request) throws CommonException{
	    
		LOGGER.info("======> REUQEST Issuer {}",request.getModel().toString());
	    
	    Optional<Issuer> issuerCode = issuerService.findByIssuerCode(request.getModel().getIssuerCode());
	    Optional<Issuer> issuerName = issuerService.findByName(request.getModel().getIssuerCode());
	    if(issuerCode.isPresent()) {
	    	throw new DuplicatedException(ErrorCode.ENTITY_EXISTED,"IssuerCode  is existed");
	    }
	    if(issuerName.isPresent()) {
	    	throw new  DuplicatedException(ErrorCode.ENTITY_EXISTED, "Name is existed");
	    }
	    String key = issuerService.createKey(request.getModel().getId());

	    if (issuerService.existInInvokingQueue(key)) {
	      LOGGER.warn("DuplicatedFromInvokingQueue with key: {}", key);
	      throw new DuplicatedException("");
	    }

	    issuerService.addToInvokingQueue(key,new Issuer());
	    request.setInvokingKey(key);

	    return true;
	  }

}
