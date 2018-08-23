package com.lapdao.transfomer.output;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

import com.lapdao.model.Issuer;
import com.lapdao.respone.IssuerRespone;

@MessageEndpoint
public class IssuerOutputTransformer extends CommonOutputTransformer {

	@Transformer(inputChannel = "issuer-create-outputTransformer-channel")
	public IssuerRespone create(Issuer issuer) {
		return transform(issuer);
	}

	private IssuerRespone transform(Issuer issuer) {
		IssuerRespone response = new IssuerRespone();

		response.setId(issuer.getId());
		response.setIssuerCode(issuer.getIssuerCode());
		response.setIssuerCode(issuer.getName());
		response.setCreatedDate(issuer.getCreatedDate());
		response.setLastUpdate(issuer.getLastUpdate());
		response.setStatus(issuer.getStatus());

		return response;
	}
}
