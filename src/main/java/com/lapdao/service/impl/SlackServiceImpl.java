package com.lapdao.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lapdao.service.SlackService;

public class SlackServiceImpl implements SlackService, DisposableBean {
	private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);

	private final Logger LOGGER;
	private final String WEBHOOK_URL;

	private @Autowired RestTemplate restTemplate;

	public SlackServiceImpl(final String WEBHOOK_URL) {
		LOGGER = LoggerFactory.getLogger(getClass());
		this.WEBHOOK_URL = WEBHOOK_URL;
	}

	@Override
	public void send(String message) {
		EXECUTOR_SERVICE.execute(() -> {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-type", "application/json");

				ResponseEntity<String> entity = restTemplate.exchange(WEBHOOK_URL, HttpMethod.POST,
						new HttpEntity<>(new SlackMessage(message), headers), String.class);

				LOGGER.info("Send message: {} to slack webhook. Response http status: {}, body: {}", message,
						entity.getStatusCodeValue(), entity.getBody());
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		});
	}

	@Override
	public void destroy() throws Exception {
		EXECUTOR_SERVICE.shutdown();
	}
}
