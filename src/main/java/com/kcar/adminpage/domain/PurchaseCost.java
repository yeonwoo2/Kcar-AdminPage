package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseCost {

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Car car;

    private int carPrice; // 가격

    private int registrationFee;

    private int managementCost;

    //==생성 메서드==//
    public static PurchaseCost createCost(Car car, int carPrice, int registrationFee, int managementCost){
        PurchaseCost purchaseCost = new PurchaseCost();
        purchaseCost.car = car;
        purchaseCost.carPrice = carPrice;
        purchaseCost.registrationFee = registrationFee;
        purchaseCost.managementCost = managementCost;
        return purchaseCost;
    }

    //총 가격 조회
    public int getTotalPrice(){
        return carPrice + registrationFee + managementCost;
    }
}
