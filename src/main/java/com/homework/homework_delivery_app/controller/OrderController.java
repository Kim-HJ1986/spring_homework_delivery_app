package com.homework.homework_delivery_app.controller;

import com.homework.homework_delivery_app.dto.OrderRequestDto;
import com.homework.homework_delivery_app.dto.OrderResponseDto;
import com.homework.homework_delivery_app.model.Orders;
import com.homework.homework_delivery_app.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public OrderResponseDto requestOrder(@RequestBody OrderRequestDto requestDto){
        return orderService.requestOrder(requestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }
}
