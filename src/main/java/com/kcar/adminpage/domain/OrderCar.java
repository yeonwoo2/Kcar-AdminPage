package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class OrderCar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Enumerated(EnumType.STRING)
    private String orderStatus;

    private LocalDateTime deadlinePaymentDate; // 주문등록시 +3일 추가해서 넣기 자동입력 될까?

    private String paymentDate; // 결제일 -> comp 수정시 등록

    @CreatedDate
    private LocalDateTime orderDate; // 주문일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderForm orderForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    //생성자 메서드//
    public static OrderCar createOrderCar(String orderStatus, OrderForm orderForm, Car car){
        OrderCar orderCar = new OrderCar();
        orderCar.orderStatus = orderStatus;
        orderCar.orderForm = orderForm;
        orderCar.car = car;
        return orderCar;
    }

    /**
     * insert시  deadlinePaymentDate = 주문일+3일
     */
    @PostPersist
    public void postDeadline(){
        System.out.println("==============hello =============");
        deadlinePaymentDate = LocalDateTime.now().plusDays(3);
    }
}
