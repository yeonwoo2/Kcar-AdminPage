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

    private int carPrice; // 가격 string ? long

    private int registrationFee;

    private int managementCost;

    private String vehicleType; // enum?

    private String seater;

    private String modelYear;

    private String mileage;

    private String color;

    private String fuel;

    private String importStatus;

    private String manufacturer;

    private String model;

    private String detailModel;

    private String transmission;

    private String accident;

    private String options;

    private String image; //img

    private String subImage; //img

    private int sheetMetal;

    private int exchange; //예약어

    private int useChange;

    private int detailedCondition;

    private int damageMyCar;

    private int relativeDamage;

    private int useChangeHistory;

    private int specialAccidentHistory;

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

















