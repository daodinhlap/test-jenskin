package com.lapdao.transfomer.input;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

import com.lapdao.common.internal.RequestPayload;
import com.lapdao.model.Issuer;
import com.lapdao.request.IssuerRequest;
import com.lapdao.service.IssuerService;

@MessageEndpoint
public class IssuerInputTRansfomer {
 
	private @Autowired IssuerService issuerSerivce;
	
	@Transformer(inputChannel = "issuer-create-inputTransformer-channel", outputChannel = "issuer-create-service-channel")
	public  Issuer create (RequestPayload<IssuerRequest> request) {
		IssuerRequest issuerRequest = request.getModel();
		
		Issuer issuer = getFromInvokingQueue(request);
		
		issuer.setIssuerCode(issuerRequest.getIssuerCode());
		issuer.setName(issuerRequest.getName());
		issuer.setCreatedDate(new Date());
		issuer.setLastUpdate(new Date());
		issuer.setStatus(Issuer.Status.ACTIVE);
		issuer.setInvokingQueue(request.getInvokingKey());
		return issuer;
	}
	
	 private Issuer getFromInvokingQueue(RequestPayload<IssuerRequest> payload) {
		    return issuerSerivce.getFromInvokingQueue(payload.getInvokingKey()).get();
		  }
}
