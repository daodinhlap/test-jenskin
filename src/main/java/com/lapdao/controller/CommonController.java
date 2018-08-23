package com.lapdao.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lapdao.common.internal.JsonMapper;
import com.lapdao.common.internal.RequestPayload;
import com.lapdao.common.internal.exception.CommonException;
import com.lapdao.common.internal.exception.ErrorCode;
import com.lapdao.common.internal.exception.UndefinedException;
import com.lapdao.respone.CommonResponse;

public abstract class CommonController<G> {

	  protected @Autowired G gateway;
	  
	  protected final Logger LOGGER;
	  
	  public CommonController() {
	    LOGGER = LoggerFactory.getLogger(getClass());
	  }

	  private <I> RequestPayload<I> createPayload(I request, Object...pathVariable) {
	    RequestPayload<I> payload = new RequestPayload<>();

	    payload.setModel(request);
	    payload.setPathVariable(pathVariable);

	    return payload;
	  }
	  
	  protected <O, I> CommonResponse<O> perform(GatewayInvoke<I, O> gatewayInvoke, HttpServletRequest httpRequest, Object...pathVariables) {
	    return this.perform(null, gatewayInvoke, httpRequest, pathVariables);
	  }

	  protected <O, I> CommonResponse<O> perform(I request, GatewayInvoke<I, O> gatewayInvoke, HttpServletRequest httpRequest, Object...pathVariables) {
	    long start = System.currentTimeMillis();
	    String token = httpRequest.getHeader("token-id");
	    try {
	      LOGGER.info("REQUEST token: {} - url: {} - method {} - input {}", 
	          token, httpRequest.getRequestURI(), httpRequest.getMethod(), request != null ? JsonMapper.write(request) : " is null");

	      O result = gatewayInvoke.invoke(createPayload(request, pathVariables));
	      CommonResponse<O> response = new CommonResponse<>();
	      response.setCode(ErrorCode.OK);
	      response.setMessage("Thành công");
	      response.setData(result);

	      logResult(token, start, response);
	      return response;
	    } catch (Exception e) {
	      return toResult(token, start, e);
	    }
	  }

	  protected <O> CommonResponse<O> toResult(String token, long start, Exception exp) {
	    LOGGER.debug(exp.getMessage(), exp);

	    if (exp instanceof CommonException) {
	      LOGGER.error(exp.getMessage());
	      CommonException ux = (CommonException) exp;
	      return create(token, start, ux);
	    }

	    Throwable throwable = exp.getCause();
	    if (throwable instanceof CommonException 
	        || throwable != null && (throwable = throwable.getCause()) instanceof CommonException) {
	      LOGGER.error(exp.getMessage());
	      return create(token, start, (CommonException) throwable);
	    }

	    LOGGER.error(exp.getMessage(), exp);
	    return create(token, start, new UndefinedException());
	  }

	  @SuppressWarnings("unchecked")
	  private <O> CommonResponse<O> create(String token, long start, CommonException ex) {
	    CommonResponse<O> response = new CommonResponse<>();

	    response.setCode(ex.getCode());
	    response.setMessage(ex.getMessage());
	    response.setData((O) ex.getData());

	    logResult(token, start, response);
	    return response;
	  }

	  protected <O> void logResult(String token, long start, CommonResponse<O> response) {
	    LOGGER.info("RESPONSE token: {} - data: {} - Duration: {}ms", token, response, (System.currentTimeMillis() - start));
	  }

	  protected interface GatewayInvoke<I, O> {
	    O invoke(RequestPayload<I> payload);
	  }
	}