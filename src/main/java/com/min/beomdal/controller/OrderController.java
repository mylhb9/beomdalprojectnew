package com.min.beomdal.controller;


import com.min.beomdal.dto.OrderRequestDto;
import com.min.beomdal.dto.OrderResponseDto;
import com.min.beomdal.entity.Menu;
import com.min.beomdal.entity.Order;
import com.min.beomdal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //주문하기
    @PostMapping("/order/request")
    public OrderResponseDto registOrders(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.saveOrder(orderRequestDto);
    }


    //주문조회
    @GetMapping("/orders")
    public List<OrderResponseDto> getorders() {
        return orderService.getAllOrders();
    }


}
