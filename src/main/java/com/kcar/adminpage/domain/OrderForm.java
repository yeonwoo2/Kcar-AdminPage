package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderForm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holderRight;

    private String number;

    private String socialSecurityNumber;

    @Embedded
    private Address address;

    private String email;

    private boolean reductionStatus;

    private String bank;

    private String accountNumber;

    private String accountHolder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne(mappedBy = "orderForm", fetch = FetchType.LAZY)
    private OrderCar orderCar;

}
