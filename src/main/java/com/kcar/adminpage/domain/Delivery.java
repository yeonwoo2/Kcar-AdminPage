package com.kcar.adminpage.domain;

import com.kcar.adminpage.enums.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiver;

    private String number;

    private String zipCode;

    private String address;

    private String detailAddress;

    private String hopeDeliveryDate; //희망 배송일

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private LocalDateTime deliveryCompleteDate; //주문시간

    private LocalDateTime modified; // 업데이트 시간 -> Auditing 기능 추가 요함

}
