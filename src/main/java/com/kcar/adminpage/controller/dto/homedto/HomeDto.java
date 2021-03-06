package com.kcar.adminpage.controller.dto.homedto;

import com.kcar.adminpage.controller.dto.InquiryOfferDto;
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
    private InquiryDto inquiryDto;
    private List<StatisticsDto> statisticsList;
    private List<InquiryOfferDto> inquiryOffer;
}
