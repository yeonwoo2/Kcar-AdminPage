package com.kcar.adminpage.repository.condition;

import com.kcar.adminpage.domain.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeliverySearchCondition {
    private String receiver;
    private DeliveryStatus deliveryStatus;
    private int paging;// 페이징
}
