package com.orderService.orderservice.controller;

import com.orderService.orderservice.model.Orders;
import com.orderService.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
@Mock
 private OrderService orderService;
@InjectMocks
private OrderController orderController;
    @Test
    void notifyForOrder() {
        Orders o=new Orders();
        Mockito.when(orderService.sendData(Collections.singletonList(o))).thenReturn("test");
        orderController.notifyForOrder(Collections.singletonList(o));
    }

    @Test
    void testNotify() {
        Orders ord=new Orders();
        Mockito.when(orderService.sendDataAndGetOrder(Collections.singletonList(ord))).thenReturn(ord);
        orderController.notify(Collections.singletonList(ord));
    }
}