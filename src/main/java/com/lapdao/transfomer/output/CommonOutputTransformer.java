package com.lapdao.transfomer.output;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonOutputTransformer {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	protected <T> Optional<T> ok(T input) {
		return Optional.of(input);
	}
}
