package com.kcar.adminpage.domain;

import com.kcar.adminpage.domain.enums.SalesStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
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
    private String image; //img -> S3 연동후 URL저장 완료

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private SalesStatus salesStatus; // READY, ON, STOP

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessor_id")
    private Assessor assessor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "purchase_cost")
    private PurchaseCost purchaseCost;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "inspection_record")
    private InspectionRecord inspectionRecord;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "insurance_history")
    private InsuranceHistory insuranceHistory;

    @CreatedDate
    private LocalDateTime registrationDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void changeCarInfo(SalesStatus salesStatus, int stockQuantity){
        this.salesStatus = salesStatus;
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
                                Assessor assessor,
                                PurchaseCost purchaseCost,
                                InspectionRecord inspectionRecord,
                                InsuranceHistory insuranceHistory){
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
        car.purchaseCost = purchaseCost;
        car.inspectionRecord = inspectionRecord;
        car.insuranceHistory = insuranceHistory;

        return car;
    }

}
