package com.lapdao.common.internal.exception;

public class InputInvalidException extends CommonException {
	private static final long serialVersionUID = -8505785301364089778L;

	public InputInvalidException(int code, String message, Object data) {
		super(code, message, data);
	}

	public InputInvalidException(int code, String message) {
		this(code, message, (Object) null);
	}

	public InputInvalidException(String message, Object data) {
		this(101, message, data);
	}

	public InputInvalidException(String message) {
		this(message, (Object) null);
	}
}