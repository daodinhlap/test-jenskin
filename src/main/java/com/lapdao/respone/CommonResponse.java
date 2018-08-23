package com.lapdao.respone;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable {
	private static final long serialVersionUID = 7767961717250712445L;
	private int code;
	private String message;
	private T data;

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String toString() {
		return "CommonResponse [code=" + this.code + ", message=" + this.message + ", data=" + this.data + "]";
	}
}