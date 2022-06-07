package com.kcar.adminpage.dto.requestdto;

import com.kcar.adminpage.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CarDto {

    @Getter @Setter
    @AllArgsConstructor
    public static class UpdateInfo{
        private String importStatus;
        private SalesStatus salesStatus;
        private boolean accident;
        private int stockQuantity;
    }

    @Getter @Setter
    public static class Info{
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
    }

    @AllArgsConstructor
    public static class responseInfo{
        private String name;
        private String carNumber;
//        private String vehicleType; // enum?
//        private String seater;
//        private String modelYear;
//        private String mileage;
//        private String color; // enum?
//        private String fuel; // enum?
//        private String importStatus; // enum?
//        private String manufacturer; // enum?
//        private String model; // enum?
//        private String detailModel; // enum?
//        private String transmission; // enum?
//        private boolean accident;
//        private String driveType; // enum?
//        private String image; //img -> 수정요함
//        private int stockQuantity;
//        private SalesStatus salesStatus; // READY, ON, STOP
    }
}
