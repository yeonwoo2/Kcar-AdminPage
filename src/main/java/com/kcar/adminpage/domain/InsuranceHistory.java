package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InsuranceHistory {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Car car;

    private int damageMyCar;

    private int relativeDamage;

    private boolean useChangeHistory;

    private boolean specialAccidentHistory; //enum? 있음 없음

    //==생성 메서드==//
    public static InsuranceHistory createInsurance(Car car, int damageMyCar, int relativeDamage, boolean useChangeHistory, boolean specialAccidentHistory){
        InsuranceHistory insuranceHistory = new InsuranceHistory();
        insuranceHistory.car = car;
        insuranceHistory.damageMyCar = damageMyCar;
        insuranceHistory.relativeDamage = relativeDamage;
        insuranceHistory.useChangeHistory = useChangeHistory;
        insuranceHistory.specialAccidentHistory = specialAccidentHistory;
        return insuranceHistory;
    }
}
