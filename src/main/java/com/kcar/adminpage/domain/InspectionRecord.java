package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InspectionRecord {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Car car;

    private int sheetMetal;

    private int exchange;  // enum?

    private boolean useChange;

    private int detailedCondition; // enum?

}
