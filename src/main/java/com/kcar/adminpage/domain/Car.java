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

    @Lob
    private String image; //img -> 수정요함

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private SalesStatus salesStatus; // READY, ON, STOP

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categories;

    @ManyToOne
    @JoinColumn(name = "assessor_id")
    private Assessor assessor;

    private LocalDateTime productRegistrationDate;

    public void SetCategory(Category categories){
        this.categories = categories;
    }

    public void SetAssessor(Assessor assessor){
        this.assessor = assessor;
    }

    public void changeCarInfo(String importStatus, SalesStatus salesStatus, boolean accident, int stockQuantity){
        this.importStatus = importStatus;
        this.salesStatus = salesStatus;
        this.accident = accident;
        this.stockQuantity = stockQuantity;
    }

    //==생성 메서드==//
    public static Car createCar(String name,
                                String carNumber,
                                String vehicleType,
                                String seater,
                                String modelYear,
                                String mileage,
                                String color,
                                String fuel,
                                String importStatus,
                                String manufacturer,
                                String model,
                                String detailModel,
                                String transmission,
                                boolean accident,
                                String driveType,
                                String image,
                                int stockQuantity,
                                SalesStatus salesStatus,
                                Category categories,
                                Assessor assessor){
        Car car = new Car();
        car.name = name;
        car.carNumber = carNumber;
        car.vehicleType = vehicleType;
        car.seater = seater;
        car.modelYear = modelYear;
        car.mileage = mileage;
        car.color = color;
        car.fuel = fuel;
        car.importStatus = importStatus;
        car.manufacturer = manufacturer;
        car.model = model;
        car.detailModel = detailModel;
        car.transmission = transmission;
        car.accident = accident;
        car.driveType = driveType;
        car.image = image;
        car.stockQuantity = stockQuantity;
        car.salesStatus = salesStatus;
        car.categories = categories;
        car.assessor = assessor;

        return car;
    }

}
