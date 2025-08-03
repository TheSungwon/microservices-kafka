package com.example.order_service.dto;

import java.util.List;

public record CreateOrderRequest (
        String customerEmail,
        List<Item> items
){
    public record Item(Long productId, Integer quantity){}

}

//    record는 Java 14부터 도입된 불변(immutable) 데이터 클래스를 간결하게 정의할 수 있는 문법입니다. 보통 DTO(Data Transfer Object), 요청/응답 모델 등에 많이 사용됩니다.
//
//✅ record 특징 요약
//    자동 생성	equals(), hashCode(), toString()
//    불변 객체	모든 필드는 final이며, 생성 후 변경 불가능
//    간결한 문법	클래스 정의, 생성자, getter를 자동으로 처리
//
//다음과 같음
//public class CreateOrderRequest {
//
//    private String customerEmail;
//    private List<Item> items;
//
//    // 생성자
//    public CreateOrderRequest(String customerEmail, List<Item> items) {
//        this.customerEmail = customerEmail;
//        this.items = items;
//    }
//
//    // 기본 생성자 (필요할 경우)
//    public CreateOrderRequest() {}
//
//    // Getter & Setter
//    public String getCustomerEmail() {
//        return customerEmail;
//    }
//
//    public void setCustomerEmail(String customerEmail) {
//        this.customerEmail = customerEmail;
//    }
//
//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }
//
//    // 중첩 클래스 Item
//    public static class Item {
//        private Long productId;
//        private Integer quantity;
//
//        public Item(Long productId, Integer quantity) {
//            this.productId = productId;
//            this.quantity = quantity;
//        }
//
//        public Item() {}
//
//        public Long getProductId() {
//            return productId;
//        }
//
//        public void setProductId(Long productId) {
//            this.productId = productId;
//        }
//
//        public Integer getQuantity() {
//            return quantity;
//        }
//
//        public void setQuantity(Integer quantity) {
//            this.quantity = quantity;
//        }
//    }
//}
//or
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CreateOrderRequest {
//
//    private String customerEmail;
//    private List<Item> items;
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class Item {
//        private Long productId;
//        private Integer quantity;
//    }
//}


