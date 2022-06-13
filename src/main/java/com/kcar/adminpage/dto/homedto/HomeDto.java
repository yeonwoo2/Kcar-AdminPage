package com.kcar.adminpage.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class HomeDto {
    private OrderAndDeliveryInfo orderAndDeliveryInfo;
    private ClaimAndCalculate claimAndCalculate;
    private CarInfoDto carInfoDto;
    private List<RecentCreateCarDto> recentCreateList;
}
