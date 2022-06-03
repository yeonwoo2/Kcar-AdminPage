package com.kcar.adminpage.domain;

import com.kcar.adminpage.enums.SalesStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter// setter 추후에 지우기
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String carNumber;

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

    private boolean accident;

    private String driveType; // enum?

    private String image; //img -> 수정요함

    private int stockQuantity;

    private LocalDateTime productRegistrationDate;

    @Enumerated(EnumType.STRING)
    private SalesStatus salesStatus; // READY, ON, STOP

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categories;

    @ManyToOne
    @JoinColumn(name = "assessor_id")
    private Assessor assessor;

    public void changeCarInfo(String carNumber, String vehicleType, String seater, String modelYear,String mileage, String color, SalesStatus salesStatus){
        this.carNumber = carNumber;
        this.vehicleType = vehicleType;
        this.seater = seater;
        this.modelYear = modelYear;
        this.mileage = mileage;
        this.color = color;
        this.salesStatus = salesStatus;
    }

}
