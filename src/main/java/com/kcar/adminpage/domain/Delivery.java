package com.kcar.adminpage.domain;

import com.kcar.adminpage.enums.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Embedded
    private Address address;

    private String hopeDeliveryDate; //희망 배송일

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus; // 수정시 배송 완료 찍힘

    private LocalDateTime deliveryCompleteDate; //배송 완료 날짜

    private LocalDateTime modifiedDate; // 업데이트 시간 -> Auditing 기능 추가 요함

}
