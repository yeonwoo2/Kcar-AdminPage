package com.kcar.adminpage.dto.responsedto;

import com.kcar.adminpage.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ResponseCarDto {

    @Getter @Setter
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
        private String categoryName;
        private String assessorName;
    }
}
