package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InsuranceHistory {

    private int damageMyCar;

    private int relativeDamage;

    private boolean useChangeHistory;

    private int specialAccidentHistory; //enum? 있음 없음
}
