package com.kcar.adminpage.controller.dto.cardto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarDetailDto {
    String name; //차량 이름
    String vehicleType; //차종
    String carNumber;//차량 번호
    String seater; //인승
    String modelYear; //연식
    String mileage; //주행거리
    String color; //색상
    String fuel; //연료
    String transmission; //변속기
    String driveType; //구동방식
    String img;
    int price;
}
