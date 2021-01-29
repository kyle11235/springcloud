package com.example.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@EnableAutoConfiguration
@RestController
@Configuration
public class StoreApplication {

    private static Logger log = LoggerFactory.getLogger(StoreApplication.class);

    @Value("${foo}")
    String name = "World";

    @Value("${api.apiGetOrders}")
    private String apiGetOrders;

    @Value("${api.apiGetProducts}")
    private String apiGetProducts;

    @Autowired
    private RestTemplate restTemplate;

	@RequestMapping("/")
    public String home() {
        log.info("Handling home");
        
        return "hello store, config name=" + name;
    }

    @RequestMapping("/orders")
    public String orders() {
        log.info("Handling home orders");

        Map<String, Object> hashMap = new HashMap<String, Object>();
        String message = restTemplate.postForObject(apiGetOrders, hashMap, String.class);
        return message;
    }

    @RequestMapping("/products")
    public String products() {
        log.info("Handling home products");

        Map<String, Object> hashMap = new HashMap<String, Object>();
        String message = restTemplate.postForObject(apiGetProducts, hashMap, String.class);
        return message;
    }

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	
	@Bean
    @LoadBalanced // fix UnknownHostException
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }






}
