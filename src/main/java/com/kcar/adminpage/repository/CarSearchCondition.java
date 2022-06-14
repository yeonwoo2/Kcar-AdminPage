package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.enums.SalesStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CarSearchCondition {

    private String carName;
    private SalesStatus saleStatus;
    private String category;
    private Integer startPrice;
    private Integer endPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
