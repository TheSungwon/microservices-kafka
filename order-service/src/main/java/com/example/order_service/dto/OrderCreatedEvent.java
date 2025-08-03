package com.example.order_service.dto;

import java.util.List;

public record OrderCreatedEvent (
        Long orderId,
        String customerName,
        List<Item> Items
){
    public record Item(Long productId, Integer quantity){}
}
