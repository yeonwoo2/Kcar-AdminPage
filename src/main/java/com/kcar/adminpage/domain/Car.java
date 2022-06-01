package com.kcar.adminpage.domain;

import com.kcar.adminpage.enums.SalesStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int carPrice; // 가격

    private int registrationFee;

    private int managementCost;

    private String vehicleType; // enum?

    private String seater;

    private String modelYear;

    private String mileage;

    private String color; // enum?

    private String fuel; // enum?

    private String importStatus; // enum?

    private String manufacturer; // enum?

    private String model; // enum?

    private String detailModel; // enum?

    private String transmission; // enum?

    private String accident; // enum?

    private String options; // enum?

    private String image; //img -> 수정요함

    private String subImage; //img -> 수정요함

    private int sheetMetal;

    private int exchange;  // enum?

    private String useChange; // enum? 있음 없음

    private int detailedCondition; // enum?

    private int damageMyCar;

    private int relativeDamage;

    private int useChangeHistory; // 있음 없음 enum?

    private int specialAccidentHistory; //enum? 있음 없음

    private LocalDateTime productRegistrationDate;

    private String carNumber;

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private SalesStatus salesStatus; // READY, ON, STOP

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categories;

    @ManyToOne
    @JoinColumn(name = "assessor_id")
    private Assessor assessor;

}
