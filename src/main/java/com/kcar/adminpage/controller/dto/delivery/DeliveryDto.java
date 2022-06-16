package com.kcar.adminpage.controller.dto.delivery;

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
        private DeliveryStatus deliveryStatus;
        private String hopeDate;
        private String deliveryCompDate;
        private String createDate;
        private String modifiedDate;
        private Address address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UpdateInfo {
        private String id;
        private String receiver;
        private DeliveryStatus deliveryStatus;
    }
}
