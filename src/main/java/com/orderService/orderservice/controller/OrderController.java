package com.orderService.orderservice.controller;

import com.orderService.orderservice.model.Orders;
import com.orderService.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //ordercontroller(consumer) end point should be used for orders
    @PostMapping("/notify")
    public String notifyForOrder(@RequestBody List<Orders> ordersList) {
        return orderService.sendData(ordersList);
    }

    @PostMapping("/notify1")
    public Orders notify(@RequestBody List<Orders> ordersList1) {
        return orderService.sendDataAndGetOrder(ordersList1);
    }


}
