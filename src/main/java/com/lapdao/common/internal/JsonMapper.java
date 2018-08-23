package com.lapdao.common.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapper.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static <O> String write(O o) {
		try {
			return MAPPER.writeValueAsString(o);
		} catch (Exception arg1) {
			LOGGER.error(arg1.getMessage(), arg1);
			return null;
		}
	}

	public static <O> O read(String string, Class<O> clazz) {
		try {
			return MAPPER.readValue(string, clazz);
		} catch (Exception arg2) {
			LOGGER.error(arg2.getMessage(), arg2);
			return null;
		}
	}

	public static <O> O read(String string, TypeReference<O> typeReference) {
		try {
			return MAPPER.readValue(string, typeReference);
		} catch (Exception arg2) {
			LOGGER.error(arg2.getMessage(), arg2);
			return null;
		}
	}

	static {
		MAPPER.setSerializationInclusion(Include.NON_NULL);
	}
}