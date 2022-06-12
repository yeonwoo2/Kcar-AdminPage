package com.kcar.adminpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class OrderCarDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GetInfo{

        private Long orderId; // 주문번호
        private String OrderStatus; //주문상태
        private String holderName; // 명의자명
        private String holderNumber; //전화번호
        private String account;// 은행
        private String accountNumber; // 계좌번호
        private String itemName; // 상품명
        private String paymentDate; //결제일
        private String deadlinePaymentDate; //입금기한
        private String orderDate; //주문일
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PostInfo{
        private String claimStatus;
        private String purchaseStatus;
        private String orderStatus;
        private int carId;
        private int orderFormId;
    }
}
