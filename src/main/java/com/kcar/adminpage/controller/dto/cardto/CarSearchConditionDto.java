package com.kcar.adminpage.controller.dto.cardto;

import com.kcar.adminpage.domain.enums.SalesStatus;
import com.kcar.adminpage.repository.condition.CarSearchCondition;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class CarSearchConditionDto {

    private String carName;
    private SalesStatus saleStatus;
    private String category;
    private Integer startPrice;
    private Integer endPrice;
    private String startDate;
    private String endDate;

    private String orderByFilter; //DESC ASC
    private int paging;// 페이징

    public CarSearchCondition toSearchCondition () {
        CarSearchCondition condition = new CarSearchCondition();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime spDate = null;
        LocalDateTime epDate = null;

        if(startDate != null && endDate != null){
            spDate = LocalDateTime.parse(this.startDate, dateTimeFormatter);
            epDate = LocalDateTime.parse(this.endDate, dateTimeFormatter);
        }

        condition.setCarName(carName);
        condition.setSaleStatus(saleStatus);
        condition.setCategory(category);
        condition.setStartPrice(startPrice);
        condition.setEndPrice(endPrice);
        condition.setStartDate(spDate);
        condition.setEndDate(epDate);
        condition.setOrderByFilter(orderByFilter);
        condition.setPaging(paging);
        return condition;
    }
}
