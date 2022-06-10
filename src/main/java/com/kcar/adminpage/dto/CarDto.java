package com.kcar.adminpage.dto;

import com.kcar.adminpage.domain.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CarDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GetInfo{
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
        private SalesStatus salesStatus; // READY, ON, STOP
        private String categoryName; // 카테고리이름
        private String assessorName; // 차량평가사 이름
    }

    @Getter @Setter
    public static class PostInfo{
        private String name;
        private String carNumber;
        private String vehicleType;
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
        private boolean accident;
        private String driveType;
        private String image;
        private int stockQuantity;
        private SalesStatus salesStatus; // READY, ON, STOP
        private String categoryName; //카테고리 이름
        private String assessorEmployeeNumber; //사번
        private PurchaseCostDto purchaseCost; // 가격정보
        private InspectionRecordDto inspectionRecord; // 성능.상태점검기록
        private InsuranceHistoryDto insuranceHistory; // 보험이력
    }

    @Getter @Setter
    @AllArgsConstructor
    public static class UpdateInfo{
        private String importStatus;
        private SalesStatus salesStatus;
        private boolean accident;
        private int stockQuantity;
    }
}
