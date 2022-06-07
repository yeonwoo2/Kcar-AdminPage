package com.kcar.adminpage.domain;

import com.kcar.adminpage.enums.ReductionStatus;
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

    @Enumerated(EnumType.STRING)
    private ReductionStatus reductionStatus;

    private String bank;

    private String accountNumber;

    private String accountHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public void SetUser(User user){
        this.user = user;
    }

    public void SetDelivery(Delivery delivery){
        this.delivery = delivery;
    }

}
