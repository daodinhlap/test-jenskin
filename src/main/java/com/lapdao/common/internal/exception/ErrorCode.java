package com.lapdao.common.internal.exception;

public class ErrorCode {
	public static final int OK = 100;
	public static final int INPUT_INVALID = 101;
	public static final int ENTITY_NOT_EXIST = 103;
	public static final int CANT_CREATE_ENTITY = 104;
	public static final int ENTITY_EXISTED = 105;
	public static final int CANT_UPDATE_ENTITY = 106;
	public static final int ENTITY_MODIFING = 107;
	public static final int ENTITY_CANT_ACCESS = 108;
	public static final int DUPLICATED = 109;
	public static final int GENERATE_CODE_FAIL = 150;
	public static final int QUERY_RATE_NO_RESULTS = 151;
	public static final int NOT_SUPPORTED_LOCATION = 152;
	public static final int NOT_FOUND_LOCATION = 153;
	public static final int QUERY_TIME_INVALID = 275;
	public static final int DATE_TIME_IS_INVALID = 277;
	public static final int ACTION_PROCCESSING = 500;
	public static final int ACCESS_DENIED = 502;
	public static final int ACTION_REJECTED = 503;
	public static final int NO_RIGHT_ACCEPTED = 504;
	public static final int DUPLICATE_REQUEST = 998;
	public static final int UNKNOWN = 999;
}