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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int damageMyCar;

    private int relativeDamage;

    private boolean useChangeHistory;

    private boolean specialAccidentHistory; //enum? 있음 없음

    //==생성 메서드==//
    public static InsuranceHistory createInsurance(int damageMyCar, int relativeDamage, boolean useChangeHistory, boolean specialAccidentHistory){
        InsuranceHistory insuranceHistory = new InsuranceHistory();
        insuranceHistory.damageMyCar = damageMyCar;
        insuranceHistory.relativeDamage = relativeDamage;
        insuranceHistory.useChangeHistory = useChangeHistory;
        insuranceHistory.specialAccidentHistory = specialAccidentHistory;
        return insuranceHistory;
    }
}
