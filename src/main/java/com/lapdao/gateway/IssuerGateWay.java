package com.lapdao.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.lapdao.common.internal.RequestPayload;
import com.lapdao.request.IssuerRequest;
import com.lapdao.respone.IssuerRespone;

@MessagingGateway
public interface IssuerGateWay {

	  @Gateway(requestChannel = "issuer-create-inputValidator-channel")
	  IssuerRespone create(RequestPayload<IssuerRequest> payload);
}
