package com.lapdao.validator.input;

import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

import com.lapdao.common.internal.RequestPayload;
import com.lapdao.common.internal.exception.CommonException;
import com.lapdao.request.IssuerRequest;

@MessageEndpoint
public class IssuerInputValidator extends CommonInputValidator {
	
	@Filter(inputChannel = "issuer-create-inputValidator-channel", outputChannel = "issuer-create-storageValidator-channel")
	public boolean create(RequestPayload<IssuerRequest> request) throws  CommonException {
		
		validateMaxLength(request.getModel().getIssuerCode(), 20, "issuerCode");
		validateMaxLength(request.getModel().getName(), 100, "name");
		
		return true;
	}

}
