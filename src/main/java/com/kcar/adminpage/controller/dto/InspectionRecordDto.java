package com.kcar.adminpage.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InspectionRecordDto {

    private int sheetMetal; //판금
    private int exchange;  // 교환
    private boolean useChange; // 용도변경
    private int detailedCondition; // 세부상태
}
