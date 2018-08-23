package com.lapdao.common.internal.exception;

public class CanNotSaveToStorageException extends CommonException {
	private static final long serialVersionUID = 2529851800791837074L;

	public CanNotSaveToStorageException(int code, String message, Object data) {
		super(code, message, data);
	}

	public CanNotSaveToStorageException(int code, String message) {
		this(code, message, (Object) null);
	}

	public CanNotSaveToStorageException(String message, Object data) {
		this(104, message, data);
	}

	public CanNotSaveToStorageException(String message) {
		this(message, (Object) null);
	}
}