package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InspectionRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sheetMetal; //판금

    private int exchange;  // 교환

    private boolean useChange; // 용도변경

    private int detailedCondition; // 세부상태

    //생성자 메서드//
    public static InspectionRecord createInspection (int sheetMetal, int exchange, boolean useChange, int detailedCondition){
        InspectionRecord inspectionRecord = new InspectionRecord();
        inspectionRecord.sheetMetal = sheetMetal;
        inspectionRecord.exchange = exchange;
        inspectionRecord.useChange = useChange;
        inspectionRecord.detailedCondition = detailedCondition;
        return inspectionRecord;
    }
}
