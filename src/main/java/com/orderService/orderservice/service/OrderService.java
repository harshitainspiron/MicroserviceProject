package com.orderService.orderservice.service;

import com.orderService.orderservice.model.Orders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderService {
    private final WebClient.Builder webClientBuilder;

    public OrderService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<Orders> sendData() {
        return webClientBuilder
                .baseUrl("http://localhost:8080/")
                .build()
                .get()
                .retrieve()
                .bodyToMono(Orders.class);
    }

    public String sendData(List<Orders> ordersList) {
        return webClientBuilder
                .baseUrl("http://localhost:8082/")
                .build()
                .post()
                .uri("/notify/orders")
                .body(BodyInserters.fromValue(ordersList))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public Orders sendDataAndGetOrder(List<Orders> ordersList) {
        return webClientBuilder
                .baseUrl("http://localhost:8082/")
                .build()
                .post()
                .uri("/notify/orders1")
                .body(BodyInserters.fromValue(ordersList))
                .retrieve()
                .bodyToFlux(Orders.class)
                .next()
                .block();
    }
}
