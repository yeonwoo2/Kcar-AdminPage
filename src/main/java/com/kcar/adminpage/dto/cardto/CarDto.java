package com.kcar.adminpage.dto.cardto;

import com.kcar.adminpage.domain.enums.SalesStatus;
import com.kcar.adminpage.dto.InspectionRecordDto;
import com.kcar.adminpage.dto.InsuranceHistoryDto;
import com.kcar.adminpage.dto.PurchaseCostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class CarDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GetInfo{
        private Long id; //상품번호
        private SalesStatus salesStatus; // 판매상태 READY, ON, STOP
        private String importStatus; // 수입여부
        private String name; //상품이름
        private String carNumber; // 차량번호
        private String vehicleType; // 차종
        private String seater;// 인승
        private String modelYear;// 연식
        private String mileage; // 주행거리
        private String color; // 색상
        private String fuel; // 연료
        private String manufacturer; // 제조사
        private String model; // 모델
        private String detailModel; // 세부모델
        private String transmission; // 변속기
        private boolean accident; // 사고유무
        private String driveType; // 구동방식
        private String categoryName; // 카테고리이름
        private String assessorName; // 차량평가사 이름
        private String shopRegion; // 지역
        private int stockQuantity; // 재고수량
        private String createDate; // 등록일
    }

    @Getter @Setter
    public static class PostInfo{

        @NotEmpty(message = "차량명을 입력해주세요")
        private String name;
        @NotEmpty(message = "차량번호를 입력해주세요")
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

        @NotEmpty(message = "사번을 입력해주세요")
        private String assessorEmployeeNumber; //사번
        private PurchaseCostDto purchaseCost; // 가격정보
        private InspectionRecordDto inspectionRecord; // 성능.상태점검기록
        private InsuranceHistoryDto insuranceHistory; // 보험이력
    }

    @Getter @Setter
    @AllArgsConstructor
    public static class UpdateInfo{
        private SalesStatus salesStatus;
        private int stockQuantity;
    }

}
