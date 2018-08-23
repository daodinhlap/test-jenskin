package com.lapdao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ImportResource("classpath:application-config.xml")
@PropertySource("classpath:application.properties")
@IntegrationComponentScan("com.lapdao.gateway")
@EnableScheduling
public class RestfullApiApplication {
	private static final int NUMBER_THREAD = 20;

    @Bean(name = "restTemplate")
    @Autowired
    public RestTemplate createRestTemplate(HttpClient httpClient) {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @Bean
    public MessagingTemplate messagingTemplate() {
        MessagingTemplate messagingTemplate = new MessagingTemplate();
        messagingTemplate.setReceiveTimeout(120 * 1000);
        return messagingTemplate;
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(NUMBER_THREAD);
    }
	public static void main(String[] args) {
		SpringApplication.run(RestfullApiApplication.class, args);
	}
}
