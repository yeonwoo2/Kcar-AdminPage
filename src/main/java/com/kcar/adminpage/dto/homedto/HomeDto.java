package com.kcar.adminpage.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class HomeDto {
    private OrderAndDeliveryInfoDto orderAndDeliveryInfoDto;
    private ClaimAndCalculate claimAndCalculate;
    private CarInfoDto carInfo;
    private List<RecentCreateCarDto> recentCreateList;
    private ReviewDto review;
    private List<RecentReviewListDto> recentReviewList;
    private UserCountDto userInfo;
    private List<StatisticsDto> statisticsList;
}
