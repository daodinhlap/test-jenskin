package com.lapdao.validator.input;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.lapdao.common.internal.exception.CommonException;
import com.lapdao.common.internal.exception.InputInvalidException;
import com.lapdao.model.QueryInput;

public abstract class CommonInputValidator {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	public static final Pattern QUERY_DATE_PATTERN = Pattern
			.compile("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[1-2])/(19[8-9][0-9]|20[0-9][0-9])$");
	public static final Pattern PATTERN = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	public static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy";

	protected void isNumber(String input, String message) throws CommonException {
		try {
			Long.parseLong(input);
		} catch (Exception arg3) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateInput(String input, String message) throws CommonException {
		if (StringUtils.isEmpty(input)) {
			throw new InputInvalidException(message);
		}
	}

	protected void compareValue(Integer a, Integer b, String message) throws CommonException {
		if (a.intValue() != b.intValue()) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMaxLength(String input, int maxLength, String message) throws CommonException {
		this.validateInput(input, message);
		if (maxLength < input.length()) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMaxLength(String input, String message) throws CommonException {
		this.validateInput(input, message);
		if (250 < input.length()) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMaxValue(Integer input, int max, String message) throws CommonException {
		if (input != null && max < input.intValue()) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMaxValue(Long input, long max, String message) throws CommonException {
		if (input != null && max < input.longValue()) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMaxValue(Short input, short max, String message) throws CommonException {
		if (input != null && max < input.shortValue()) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMinValue(Integer input, int min, String message) throws CommonException {
		if (input != null && input.intValue() < min) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMinValue(Integer input, String message) throws CommonException {
		if (input != null && input.intValue() < 0) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMinValue(Long input, long min, String message) throws CommonException {
		if (input != null && input.longValue() < min) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMinValue(Long input, String message) throws CommonException {
		if (input != null && input.longValue() < 0L) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMinValue(Short input, short min, String message) throws CommonException {
		if (input != null && input.shortValue() < min) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMinValue(Short input, String message) throws CommonException {
		if (input != null && input.shortValue() < 0) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateMinValue(Double value, double minValue, String field) throws CommonException {
		if (value == null || value.doubleValue() < minValue) {
			throw new InputInvalidException(101, field);
		}
	}

	protected void validateMinValue(Double value, String field) throws CommonException {
		if (value == null || value.doubleValue() < 0.0D) {
			throw new InputInvalidException(101, field);
		}
	}

	protected void validateMinValue(Float value, float minValue, String field) throws CommonException {
		if (value == null || value.floatValue() < minValue) {
			throw new InputInvalidException(101, field);
		}
	}

	protected void validateMinValue(Float value, String field) throws CommonException {
		if (value == null || value.floatValue() < 0.0F) {
			throw new InputInvalidException(101, field);
		}
	}

	protected void validateRangeValue(Integer input, int min, int max, String message) throws CommonException {
		this.validateMinValue(input, min, message);
		this.validateMaxValue(input, max, message);
	}

	protected void validateRangeValue(Long input, long min, long max, String message) throws CommonException {
		this.validateMinValue(input, min, message);
		this.validateMaxValue(input, max, message);
	}

	protected void validateRangeValue(Short input, short min, short max, String message) throws CommonException {
		this.validateMinValue(input, min, message);
		this.validateMaxValue(input, max, message);
	}

	protected <O> void validateEmpty(O obj, String message) throws CommonException {
		if (ObjectUtils.isEmpty(obj)) {
			throw new InputInvalidException(101, message);
		}
	}

	protected <E> void validateEmpty(Collection<E> obj, String message) throws CommonException {
		if (CollectionUtils.isEmpty(obj)) {
			throw new InputInvalidException(101, message);
		}
	}

	protected void validateValueLengthNullable(String data, int maxLength, String fieldName) throws CommonException {
		if (!StringUtils.isEmpty(data) && data.length() > maxLength) {
			throw new InputInvalidException(101, fieldName);
		}
	}

	protected void validateValueLengthNullable(String data, String fieldName) throws CommonException {
		if (!StringUtils.isEmpty(data) && data.length() > 250) {
			throw new InputInvalidException(101, fieldName);
		}
	}

	protected boolean isValidIp(String ip) {
		return PATTERN.matcher(ip).matches();
	}

	protected <I extends QueryInput> void validateFromDateAndToDate(I input) throws CommonException {
		this.validateFromDateAndToDate(input.getFromDate(), input.getToDate());
	}

	protected void validateDate(String input, String message) throws CommonException {
		if (!QUERY_DATE_PATTERN.matcher(input).matches()) {
			throw new InputInvalidException(message);
		}
	}

	protected void validateFromDateAndToDate(String fromDate, String toDate) throws CommonException {
		if (fromDate != null && toDate != null) {
			Matcher from = QUERY_DATE_PATTERN.matcher(fromDate);
			Matcher to = QUERY_DATE_PATTERN.matcher(toDate);
			if (!from.matches() || !to.matches() || !this.isFromLessThanToDate(from, to)) {
				throw new InputInvalidException("Khoảng thời gian truy vấn không hợp lệ");
			}
		} else {
			throw new InputInvalidException("Khoảng thời gian truy vấn không hợp lệ");
		}
	}

	private boolean isFromLessThanToDate(Matcher fromDate, Matcher toDate) {
		return Integer.parseInt(fromDate.group(3)) > Integer.parseInt(toDate.group(3))
				? false
				: (Integer.parseInt(fromDate.group(3)) == Integer.parseInt(toDate.group(3))
						&& Integer.parseInt(fromDate.group(2)) > Integer.parseInt(toDate.group(2))
								? false
								: Integer.parseInt(fromDate.group(3)) != Integer.parseInt(toDate.group(3))
										|| Integer.parseInt(fromDate.group(2)) != Integer.parseInt(toDate.group(2))
										|| Integer.parseInt(fromDate.group(1)) <= Integer.parseInt(toDate.group(1)));
	}
}