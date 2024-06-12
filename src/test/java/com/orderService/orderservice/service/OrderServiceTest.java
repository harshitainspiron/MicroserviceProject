package com.orderService.orderservice.service;

import com.orderService.orderservice.model.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
//Mockito mocking (any, eq, verify)
class OrderServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private OrderService orderService;
//It ensures that setUp method runs first before each method
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //Initializes the mocks defined in the test class.
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(BodyInserter.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    void testSendData1() {
        // Arrange
        String expectedResponse = "Received order: [orders(id=0, product=product, quantity=0)]";
        List<Orders> ordersList = List.of(new Orders(0, "product", 0));

        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // Act
        String actualResponse = orderService.sendData(ordersList);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(webClientBuilder).build();
        verify(webClient).post();
        verify(requestBodyUriSpec).uri(eq("/notify/orders"));
        verify(requestBodySpec).body(any(BodyInserter.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(String.class);
    }

    @Test
    void testSendData() {
        // Arrange
        String expectedResponse = "Received order: [orders(id=0, product=product, quantity=0)]";
        List<Orders> ordersList = List.of(new Orders(0, "product", 0));

        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // Act
        String actualResponse = orderService.sendData(ordersList);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(webClientBuilder).build();
        verify(webClient).post();
        verify(requestBodyUriSpec).uri(eq("/notify/orders"));
        verify(requestBodySpec).body(any(BodyInserter.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(String.class);
    }

    @Test
    void testSendDataAndGetOrder() {
        // Arrange
        Orders expectedOrder = new Orders(0, "product", 0);
        List<Orders> ordersList = List.of(expectedOrder);

        when(responseSpec.bodyToFlux(Orders.class)).thenReturn(Flux.just(expectedOrder));

        // Act
        Orders actualOrder = orderService.sendDataAndGetOrder(ordersList);

        // Assert
        assertEquals(expectedOrder, actualOrder);

        // Verify interactions
        verify(webClientBuilder).build();
        verify(webClient).post();
        verify(requestBodyUriSpec).uri(eq("/notify/orders1"));
        verify(requestBodySpec).body(any(BodyInserter.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToFlux(Orders.class);
    }
}