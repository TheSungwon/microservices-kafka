package com.example.inventory_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @Autowired
    private StockDecreasedProducer stockDecreasedProducer;

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void listen(ConsumerRecord<String, String> record){

        String orderEvent = record.value();

        System.out.println("✅ 주문 이벤트 수신: " + orderEvent);

        // 여기서 재고 차감 로직이 들어간다고 가정
        System.out.println("📦 재고 차감 완료");

        // 차감 완료 후 stock-decreased 이벤트 발행
//        stockDecreasedProducer.sendStockDecreasedEvent("재고 차감 완료 for order: " + orderEvent);
        try{

        stockDecreasedProducer.publishStockDecreasedEvent("123123");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        // TODO: 재고 차감 로직 처리 후
        // stock-decreased 이벤트 발행하기

    }
}
