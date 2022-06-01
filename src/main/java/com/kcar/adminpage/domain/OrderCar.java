package com.kcar.adminpage.domain;

import com.kcar.adminpage.enums.ClaimStatus;
import com.kcar.adminpage.enums.OrderStatus;
import com.kcar.adminpage.enums.PurchaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime deadlinePayment; // 주문등록시 +3일 추가해서 넣기

    private LocalDateTime paymentDate; // 결제일

    private LocalDateTime orderDate; // 주문일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderForm orderForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
