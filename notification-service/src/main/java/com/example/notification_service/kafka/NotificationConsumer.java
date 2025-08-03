package com.example.notification_service.kafka;


import com.example.notification_service.dto.StockDecreasedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "stock-decreased", groupId = "notification-group")
    public void consume(String message){
        System.err.println("📢 재고 차감 이벤트 수신됨: " + message);
        // 실제로는 이메일이나 슬랙 알림 같은 로직
    }

    @KafkaListener(topics = "stock-decreased", groupId = "notification-group",
    containerFactory = "kafkaListenerContainerFactory") // KafkaConfig = kafkaListenerContainerFactory
    public void consume(StockDecreasedEvent event){
        System.out.println("🔔 알림: 재고 차감됨 - 주문 ID: " + event.getOrderId() +
                ", message : " + event.getMessage()
                );
    }
}
//[order-service]
//        └── (order-created 메시지)
//        ↓ Kafka (order-created 토픽)
//
//[inventory-service]
//        └── order-created 메시지 수신
//    └── 재고 차감 처리
//    └── stock-decreased 메시지 발행
//          ↓ Kafka (stock-decreased 토픽)
//
//[notification-service]
//        └── stock-decreased 메시지 수신
//    └── 🔔 알림 로그 출력