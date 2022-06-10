package com.kcar.adminpage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsuranceHistoryDto {

    private int damageMyCar; //내차피해
    private int relativeDamage; // 상대차피해
    private boolean useChangeHistory; // 용도변경이력
    private boolean specialAccidentHistory;// 특수사고이력
}
