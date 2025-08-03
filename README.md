# Kafka를 이용한 마이크로서비스 프로젝트

이 프로젝트는 Spring Boot와 Kafka를 사용하여 구축된 마이크로서비스 아키텍처의 간단한 예제입니다.

## 프로젝트 구조

이 프로젝트는 세 개의 주요 마이크로서비스로 구성됩니다.

- **order-service**: 주문 생성 및 관리를 담당합니다. 주문이 생성되면 `OrderCreatedEvent`를 Kafka 토픽으로 발행합니다.
- **inventory-service**: 상품 재고를 관리합니다. `OrderCreatedEvent`를 구독하여 재고를 감소시키고, 재고가 성공적으로 감소되면 `StockDecreasedEvent`를 발행합니다.
- **notification-service**: 사용자에게 알림을 보내는 역할을 합니다. `StockDecreasedEvent`를 구독하여 주문 처리 상태에 대한 알림을 보냅니다.

## 아키텍처 개요

```
[Client] -> [order-service] --(OrderCreatedEvent)--> [Kafka] --(OrderCreatedEvent)--> [inventory-service]
                                                                    |
                                                                    |
                               --(StockDecreasedEvent)--> [Kafka] --(StockDecreasedEvent)--> [notification-service]
```

1.  사용자가 `order-service`에 주문을 요청합니다.
2.  `order-service`는 주문을 데이터베이스에 저장하고 `order-created` 토픽으로 메시지를 보냅니다.
3.  `inventory-service`는 `order-created` 토픽을 구독하고 있다가, 메시지를 받으면 상품 재고를 줄입니다.
4.  재고가 성공적으로 줄어들면, `inventory-service`는 `stock-decreased` 토픽으로 메시지를 보냅니다.
5.  `notification-service`는 `stock-decreased` 토픽을 구독하고 있다가, 메시지를 받으면 사용자에게 알림을 보냅니다.

## 기술 스택

- **Framework**: Spring Boot
- **Language**: Java
- **Messaging Queue**: Apache Kafka
- **Build Tool**: Maven
- **Containerization**: Docker, Docker Compose

## 실행 방법

### Docker Compose 사용 (권장)

프로젝트 루트 디렉토리에서 다음 명령어를 실행하여 모든 서비스를 한 번에 시작할 수 있습니다.

```bash
docker-compose up --build
```

### 개별 서비스 실행

각 서비스 디렉토리로 이동하여 다음 Maven 명령어를 사용하여 개별적으로 실행할 수도 있습니다.

```bash
# 예: order-service 실행
cd order-service
./mvnw spring-boot:run
```

각 서비스는 자체 `application.yml` 파일에 정의된 포트에서 실행됩니다.
