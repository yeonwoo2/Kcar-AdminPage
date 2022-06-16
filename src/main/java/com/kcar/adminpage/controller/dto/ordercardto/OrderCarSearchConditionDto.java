package com.kcar.adminpage.controller.dto.ordercardto;

import com.kcar.adminpage.domain.enums.OrderStatus;
import com.kcar.adminpage.repository.condition.OrderCarSearchCondition;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class OrderCarSearchConditionDto {
    private OrderStatus orderStatus; //주문상태
    private String name; //명의자명
    private String itemName; //차량이름

    //입금날짜
    private String payStartDate;
    private String payEndDate;

    //주문날짜
    private String orderStartDate;
    private String orderEndDate;

    private Integer paging;// 페이징

    public OrderCarSearchCondition toSearchCondition () {
        OrderCarSearchCondition condition = new OrderCarSearchCondition();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime paySpDate = null;
        LocalDateTime payEnDate = null;

        LocalDateTime orderSpDate = null;
        LocalDateTime orderEnDate = null;

        if(payStartDate != null && payEndDate != null){
            paySpDate = LocalDateTime.parse(this.payStartDate, dateTimeFormatter);
            payEnDate = LocalDateTime.parse(this.payEndDate, dateTimeFormatter);
        }

        if(orderStartDate != null && orderEndDate != null){
            orderSpDate = LocalDateTime.parse(this.orderStartDate, dateTimeFormatter);
            orderEnDate = LocalDateTime.parse(this.orderEndDate, dateTimeFormatter);
        }

        condition.setOrderStatus(orderStatus);
        condition.setName(name);
        condition.setItemName(itemName);
        condition.setPayStartDate(paySpDate);
        condition.setPayEndDate(payEnDate);
        condition.setOrderStartDate(orderSpDate);
        condition.setOrderEndDate(orderEnDate);
        condition.setPaging(paging);
        return condition;
    }
}
