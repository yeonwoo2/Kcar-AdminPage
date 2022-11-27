package com.kcar.adminpage.controller.dto.cardto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarPriceDto {

    private Integer carPrice; // 가격

    private Integer registrationFee; //등록비

    private Integer managementCost; // 관리비

}
