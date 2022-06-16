package com.kcar.adminpage.controller.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClaimAndCalculate {

    private int cancelOrder;
    private int returnOrder;
    private int todayCalculate;
}
