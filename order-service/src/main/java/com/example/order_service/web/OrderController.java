package com.example.order_service.web;


import com.example.order_service.domain.Order;
import com.example.order_service.domain.OrderItem;
import com.example.order_service.dto.CreateOrderRequest;
import com.example.order_service.dto.OrderCreatedEvent;
import com.example.order_service.kafka.OrderEventProducer;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderEventProducer  orderEventProducer;

    @PostMapping
    @Transactional
    public Long createOrder(@RequestBody CreateOrderRequest req) {
        System.err.print(req.toString());

        //db save
        Order order = Order.builder()
                .customerEmail(req.customerEmail())
                .items(new ArrayList<>())
                .build();

        req.items().forEach(i ->
                order.addItem(OrderItem.builder()
                        .productId(i.productId())
                        .quantity(i.quantity()).build())
        );

        Order saved = orderRepository.save(order);

        //kafka event
        OrderCreatedEvent event = new OrderCreatedEvent(
                saved.getId(),
                saved.getCustomerEmail(),
                req.items().stream().map(i ->
                        new OrderCreatedEvent.Item(
                                i.productId(),
                                i.quantity()
                        )).toList()
        );

        orderEventProducer.sendOrderCreated(event);

        System.err.println(saved + "\n" + event + "\n" + saved.getId());

        return saved.getId();
    }

}
