package com.lapdao.common.internal.exception;

public class CommonException  extends Exception{

	private static final long serialVersionUID = -2840772241364268366L;
	protected int code;
	protected Object data;

	public CommonException(int code, String message, Object data) {
		super(message);
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return this.code;
	}

	public Object getData() {
		return this.data;
	}
}
