package com.example.order_service.kafka;


import com.example.order_service.config.kafkaConfig;
import com.example.order_service.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    //Kafka 메시지를 전송할 때 사용하는 Spring Kafka의 템플릿.
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;


    //OrderCreatedEvent 객체를 Kafka의 토픽에 전송함
    //Kafka는 이 이벤트를 비동기적으로 브로커에 전송하고, 소비자들이 이 토픽을 구독하고 있으면 메시지를 받게 됨
    public void sendOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send(kafkaConfig.ORDER_CREATED_TOPIC, event);
    }
}
