package com.kcar.adminpage.dto.cardto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CarStatusInfoDto {
    private int totalQuantity;
    private int ready;
    private int on;
    private int stop;
}
