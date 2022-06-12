package com.kcar.adminpage.dto;

import com.kcar.adminpage.domain.Address;
import com.kcar.adminpage.domain.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class DeliveryDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GetInfo{
        private Long deliveryId;
        private String receiver;
        private String PhoneNumber;
        private Address address;
        private DeliveryStatus deliveryStatus;
        private String hopeDate;
        private String deliveryCompDate;
        private String createDate;
        private String modifiedDate;
    }
}
