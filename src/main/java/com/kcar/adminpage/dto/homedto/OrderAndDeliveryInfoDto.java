package com.kcar.adminpage.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderAndDeliveryInfoDto {

    private int waitPayment;
    private int compPayment;
    private int deliveryReady;
    private int deliveryGoing;
    private int deliveryComp;
}
