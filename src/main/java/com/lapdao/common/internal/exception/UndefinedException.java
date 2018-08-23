package com.lapdao.common.internal.exception;

public class UndefinedException extends CommonException {
	private static final long serialVersionUID = -4041104568800356004L;

	public UndefinedException(Object data) {
		super(999, "Lỗi không xác định", data);
	}

	public UndefinedException() {
		this((Object) null);
	}
}
