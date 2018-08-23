package com.lapdao.common.internal.exception;

public class NotFoundEntityException extends CommonException {
	private static final long serialVersionUID = 3871763949534138651L;

	public NotFoundEntityException(int code, String message, Object data) {
		super(code, message, data);
	}

	public NotFoundEntityException(int code, String message) {
		this(code, message, (Object) null);
	}

	public NotFoundEntityException(String message, Object data) {
		this(103, message, data);
	}

	public NotFoundEntityException(String message) {
		this(message, (Object) null);
	}
}