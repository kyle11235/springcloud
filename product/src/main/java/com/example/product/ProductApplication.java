package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ProductApplication {

	@RequestMapping("/")
    public String home() {
        return "hello product";
    }

    @RequestMapping("/products")
    public String products() {
        return "products";
    }

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
    @LoadBalanced // fix UnknownHostException
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
