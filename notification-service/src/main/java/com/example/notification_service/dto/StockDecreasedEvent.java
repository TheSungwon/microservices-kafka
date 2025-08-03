package com.example.notification_service.dto;

import lombok.Data;

@Data
public class StockDecreasedEvent {

    private String orderId;
    private String message;

}
