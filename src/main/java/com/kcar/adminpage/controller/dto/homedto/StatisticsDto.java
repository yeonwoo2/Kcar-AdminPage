package com.kcar.adminpage.controller.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class StatisticsDto {
    private String date;
    private int count;
}
