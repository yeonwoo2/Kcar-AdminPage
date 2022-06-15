package com.kcar.adminpage.repository.condition;

import com.kcar.adminpage.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class OrderCarSearchCondition {
    private OrderStatus orderStatus; //주문상태
    private String name; //명의자명
    private String itemName; //차량이름

    //입금날짜
    private LocalDateTime payStartDate;
    private LocalDateTime payEndDate;

    //주문날짜
    private LocalDateTime orderStartDate;
    private LocalDateTime orderEndDate;

    private int paging;// 페이징
}
