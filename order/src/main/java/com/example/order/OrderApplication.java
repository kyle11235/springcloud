package com.example.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;


@SpringBootApplication
@RestController
public class OrderApplication {

    private static Logger log = LoggerFactory.getLogger(OrderApplication.class);

    @RequestMapping("/")
    public String home() {
        log.info("Handling order");

        return "hello order";
    }

    @RequestMapping("/orders")
    public String orders() {
        log.info("Handling order orders");

        return "orders";
    }

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
    @LoadBalanced // fix UnknownHostException
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
