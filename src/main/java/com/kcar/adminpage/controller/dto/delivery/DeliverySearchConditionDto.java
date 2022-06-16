package com.kcar.adminpage.controller.dto.delivery;

import com.kcar.adminpage.domain.enums.DeliveryStatus;
import com.kcar.adminpage.repository.condition.DeliverySearchCondition;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeliverySearchConditionDto {
    private String receiver;
    private DeliveryStatus deliveryStatus;
    private int paging;// 페이징

    public DeliverySearchCondition toSearchCondition() {
        DeliverySearchCondition condition = new DeliverySearchCondition();
        condition.setReceiver(receiver);
        condition.setDeliveryStatus(deliveryStatus);
        condition.setPaging(paging);
        return condition;
    }
}
