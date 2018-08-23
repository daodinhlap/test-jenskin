package com.lapdao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lapdao.gateway.IssuerGateWay;
import com.lapdao.request.IssuerRequest;
import com.lapdao.respone.CommonResponse;

@RestController
@RequestMapping("/issuers")
public class IssuerController extends CommonController<IssuerGateWay> {
	
	@PostMapping
	public CommonResponse<?> create(@RequestBody IssuerRequest request, HttpServletRequest httpRequest) {
		return  perform(request, gateway::create, httpRequest) ;

	}

}
