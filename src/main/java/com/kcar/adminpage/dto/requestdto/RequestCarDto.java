package com.kcar.adminpage.dto.requestdto;

import com.kcar.adminpage.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class RequestCarDto {

    @Getter @Setter
    @AllArgsConstructor
    public static class UpdateInfo{
        private String importStatus;
        private SalesStatus salesStatus;
        private boolean accident;
        private int stockQuantity;
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
    }

}
