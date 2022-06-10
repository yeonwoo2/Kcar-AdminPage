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

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Car car;

    private int sheetMetal; //판금

    private int exchange;  // 교환

    private boolean useChange; // 용도변경

    private int detailedCondition; // 세부상태

    //생성자 메서드//
    public static InspectionRecord createInspection (Car car, int sheetMetal, int exchange, boolean useChange, int detailedCondition){
        InspectionRecord inspectionRecord = new InspectionRecord();
        inspectionRecord.car = car;
        inspectionRecord.sheetMetal = sheetMetal;
        inspectionRecord.exchange = exchange;
        inspectionRecord.useChange = useChange;
        inspectionRecord.detailedCondition = detailedCondition;
        return inspectionRecord;
    }
}
