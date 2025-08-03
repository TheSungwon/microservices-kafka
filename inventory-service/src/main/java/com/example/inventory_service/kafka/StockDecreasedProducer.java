package com.example.inventory_service.kafka;


import com.example.inventory_service.dto.StockDecreasedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class StockDecreasedProducer {

    private static final String TOPIC = "stock-decreased";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, StockDecreasedEvent> kafkaDTOTemplate;

    //문자열 용
//    public void sendStockDecreasedEvent(String message){
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("📤 stock-decreased 이벤트 발행: " + message);
//    }

    //DTO 용
    public void publishStockDecreasedEvent(String orderId){
        StockDecreasedEvent event = new StockDecreasedEvent(orderId, "Stock decreased successfully");
        System.err.println(event);
        kafkaDTOTemplate.send(TOPIC, event);
        System.out.println("📤 publishStockDecreasedEvent 이벤트 발행: " + orderId);
    }
}
//src
//└── main
//    └── java
//        └── com.example.inventory_service
//            ├── controller   (선택)
//            ├── service
//            ├── kafka
//            │   ├── OrderCreatedConsumer.java  ← 여기서 메시지 받기
//            │   └── StockDecreasedProducer.java ← 여기서 메시지 보내기
//            └── dto