package com.lapdao.common.internal;

import java.util.Date;

public class RequestPayload<T> {
	protected Date fromDate;
	protected Date toDate;
	protected int size = 20;
	protected int page;
	private String sourceIp;
	private T model;
	private Object[] pathVariable;
	private String invokingKey;

	public RequestPayload() {
	}

	public RequestPayload(String sourceIp, T data) {
		this.sourceIp = sourceIp;
		this.model = data;
	}

	public String getSourceIp() {
		return this.sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public T getModel() {
		return this.model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public String getInvokingKey() {
		return this.invokingKey;
	}

	public void setInvokingKey(String invokingKey) {
		this.invokingKey = invokingKey;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Object[] getPathVariable() {
		return this.pathVariable;
	}

	public void setPathVariable(Object[] pathVariable) {
		this.pathVariable = pathVariable;
	}

	public <P> P getPathVariable(int index) {
		return this.pathVariable != null ? (P) this.pathVariable[index] : null;
	}

	public <I> RequestPayload<I> clone(I model) {
		RequestPayload payload = new RequestPayload();
		payload.setSourceIp(this.sourceIp);
		payload.setFromDate(this.fromDate);
		payload.setToDate(this.toDate);
		payload.setInvokingKey(this.invokingKey);

		payload.setModel(model);
		return payload;
	}
}
