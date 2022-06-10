package com.kcar.adminpage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PurchaseCostDto {

    private int carPrice; // 가격
    private int registrationFee;
    private int managementCost;
}
