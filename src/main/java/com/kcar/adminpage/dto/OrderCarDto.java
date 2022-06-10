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
        private Long orderCarId;
        private String deadlinePaymentDate; //마감기한
        private String OrderStatus;
        private String carName;
        private String accountNumber;
        private String account;
        private String holderName;
        private String holderNumber;
        private String claimStatus;
        private String purchaseStatus;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PostInfo{
        private Long orderCarId;
        private String claimStatus;
        private String purchaseStatus;
        private String orderStatus;
        private String deadlinePaymentDate;
        private String paymentDate;
        private String orderDate;
        private Long carId;
        private Long orderId;
    }
}
