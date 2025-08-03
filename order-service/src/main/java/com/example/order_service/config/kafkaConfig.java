package com.example.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class kafkaConfig {

    public static final String ORDER_CREATED_TOPIC = "order-created";

    @Bean
    public NewTopic orderCreatedTopic(){

        return new NewTopic(ORDER_CREATED_TOPIC, 1, (short) 1);
    }
}
//토픽 (Topic)	Kafka에서 메시지를 저장하고 전달하는 채널(이름)
//NewTopic	토픽을 만들기 위한 설정을 담은 객체 (토픽 생성용)

//1. kafka 클러스터 내 에서 토픽 식별명
//2. 파티션 개수
//3. 복제 개수 (보통 2~3, 남은 서버)