package com.kcar.adminpage.domain;

import com.kcar.adminpage.domain.enums.DeliveryStatus;
import com.kcar.adminpage.domain.enums.SalesStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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

    private String deliveryCompleteDate; //배송 완료 날짜

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 업데이트 시간

    @CreatedDate
    private LocalDateTime createDate;

    public void changeDeliveryInfo(String receiver, String number, Address address, DeliveryStatus deliveryStatus, String hopeDate){
        this.receiver = receiver;
        this.number = number;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.deliveryCompleteDate = hopeDate;
    }

}
