package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.*;
import com.kcar.adminpage.domain.enums.SalesStatus;
import com.kcar.adminpage.dto.CarDto;
import com.kcar.adminpage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;
    private final AssessorRepository assessorRepository;
    private final PurchaseCostRepository purchaseCostRepository;
    private final InsuranceHistoryRepository insuranceHistoryRepository;
    private final InspectionRecordRepository inspectionRecordRepository;
    private final OrderCarRepository orderCarRepository;



    @Transactional // 차량 등록
    public void saveCar(CarDto.PostInfo info){

        Category category = categoryRepository.findByName(info.getCategoryName());// 단건 조회
        Assessor assessor = assessorRepository.findByEmployeeNumber(info.getAssessorEmployeeNumber());//단건 조회


        Car car = Car.createCar(info.getName(),
                                info.getCarNumber(),
                                info.getVehicleType(),
                                info.getSeater(),
                                info.getModelYear(),
                                info.getMileage(),
                                info.getColor(),
                                info.getFuel(),
                                info.getImportStatus(),
                                info.getManufacturer(),
                                info.getModel(),
                                info.getDetailModel(),
                                info.getTransmission(),
                                info.isAccident(),
                                info.getDriveType(),
                                info.getImage(),
                                info.getStockQuantity(),
                                info.getSalesStatus(),
                                category,
                                assessor);

        //차량 가격 생성
        PurchaseCost purchaseCost = PurchaseCost.createCost(car, info.getPurchaseCost().getCarPrice(),
                                                                 info.getPurchaseCost().getRegistrationFee(),
                                                                 info.getPurchaseCost().getManagementCost());
        //보험이력 생성
        InsuranceHistory insuranceHistory = InsuranceHistory.createInsurance(car, info.getInsuranceHistory().getDamageMyCar(),
                                                                                  info.getInsuranceHistory().getRelativeDamage(),
                                                                                  info.getInsuranceHistory().isUseChangeHistory(),
                                                                                  info.getInsuranceHistory().isSpecialAccidentHistory());

        InspectionRecord inspectionRecord = InspectionRecord.createInspection(car, info.getInspectionRecord().getSheetMetal(),
                                                                                   info.getInspectionRecord().getExchange(),
                                                                                   info.getInspectionRecord().isUseChange(),
                                                                                   info.getInspectionRecord().getDetailedCondition());

        inspectionRecordRepository.save(inspectionRecord);
        insuranceHistoryRepository.save(insuranceHistory);
        purchaseCostRepository.save(purchaseCost);
        carRepository.save(car);
    }

    @Transactional // 차량 업데이트
    public void updateCar(Long carId, CarDto.UpdateInfo updateInfo){
        Car findCar = carRepository.findOne(carId);
        findCar.changeCarInfo(updateInfo.getSalesStatus(), updateInfo.getStockQuantity());
    }

    //모든차량 검색
    public List<CarDto.GetInfo> findAllCarInfo(){
        List<Car> carList = carRepository.findAllWithCategoryAndAssessor();
        return carList.stream()
                .map(c -> new CarDto.GetInfo(c.getName(),
                                                    c.getCarNumber(),
                                                    c.getVehicleType(),
                                                    c.getSeater(),
                                                    c.getModelYear(),
                                                    c.getMileage(),
                                                    c.getColor(),
                                                    c.getFuel(),
                                                    c.getImportStatus(),
                                                    c.getManufacturer(),
                                                    c.getModel(),
                                                    c.getDetailModel(),
                                                    c.getTransmission(),
                                                    c.isAccident(),
                                                    c.getDriveType(),
                                                    c.getImage(),
                                                    c.getStockQuantity(),
                                                    c.getSalesStatus(),
                                                    c.getCategories().getName(),
                                                    c.getAssessor().getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCar(Long id) {
        Car car = carRepository.findOne(id);
        if(!car.getSalesStatus().equals(SalesStatus.STOP)){
            throw new IllegalStateException("판매중지된 상품이 아닙니다.");
        }

        inspectionRecordRepository.delete(id); //연관관계 제거
        insuranceHistoryRepository.delete(id); //연관관계 제거
        purchaseCostRepository.delete(id); //연관관계 제거
        carRepository.delete(car); //차량제거
    }
}
