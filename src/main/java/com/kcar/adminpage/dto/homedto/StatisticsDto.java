package com.kcar.adminpage.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class StatisticsDto {
    private String date;
    private int count;
}
