package com.lapdao.common.internal.exception;

public class DuplicatedException extends CommonException {
	private static final long serialVersionUID = 6940977217335836311L;

	public DuplicatedException(int code, String message, Object data) {
		super(code, message, data);
	}

	public DuplicatedException(int code, String message) {
		this(code, message, (Object) null);
	}

	public DuplicatedException(String message, Object data) {
		this(109, message, data);
	}

	public DuplicatedException(String message) {
		this(message, (Object) null);
	}
}