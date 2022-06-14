package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.*;
import com.kcar.adminpage.domain.enums.SalesStatus;
import com.kcar.adminpage.dto.cardto.CarDto;
import com.kcar.adminpage.dto.cardto.CarSearchConditionDto;
import com.kcar.adminpage.dto.cardto.CarStatusInfoDto;
import com.kcar.adminpage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
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

    @Transactional // 차량 등록
    public void saveCar(CarDto.PostInfo info){

        Category category = categoryRepository.findByName(info.getCategoryName());// 단건 조회
        Assessor assessor = assessorRepository.findByEmployeeNumber(info.getAssessorEmployeeNumber());//단건 조회

        //차량 가격 생성
        PurchaseCost purchaseCost = PurchaseCost.createCost(info.getPurchaseCost().getCarPrice(),
                info.getPurchaseCost().getRegistrationFee(),
                info.getPurchaseCost().getManagementCost());

        InspectionRecord inspectionRecord = InspectionRecord.createInspection(info.getInspectionRecord().getSheetMetal(),
                info.getInspectionRecord().getExchange(),
                info.getInspectionRecord().isUseChange(),
                info.getInspectionRecord().getDetailedCondition());

        //보험이력 생성
        InsuranceHistory insuranceHistory = InsuranceHistory.createInsurance(info.getInsuranceHistory().getDamageMyCar(),
                info.getInsuranceHistory().getRelativeDamage(),
                info.getInsuranceHistory().isUseChangeHistory(),
                info.getInsuranceHistory().isSpecialAccidentHistory());


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
                                assessor,
                                purchaseCost,
                                inspectionRecord,
                                insuranceHistory);

        purchaseCostRepository.save(purchaseCost);
        inspectionRecordRepository.save(inspectionRecord);
        insuranceHistoryRepository.save(insuranceHistory);
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
                .map(c -> new CarDto.GetInfo(c.getId(),
                                                    c.getSalesStatus(),
                                                    c.getImportStatus(),
                                                    c.getName(),
                                                    c.getCarNumber(),
                                                    c.getVehicleType(),
                                                    c.getSeater(),
                                                    c.getModelYear(),
                                                    c.getMileage(),
                                                    c.getColor(),
                                                    c.getFuel(),
                                                    c.getManufacturer(),
                                                    c.getModel(),
                                                    c.getDetailModel(),
                                                    c.getTransmission(),
                                                    c.isAccident(),
                                                    c.getDriveType(),
                                                    c.getCategories().getName(),
                                                    c.getAssessor().getName(),
                                                    c.getAssessor().getDirectShop(),
                                                    c.getStockQuantity(),
                                                    c.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                .collect(Collectors.toList());
    }

    public CarStatusInfoDto findCarBySaleStatus(){
        List<Car> all = carRepository.findAll();
        List<Car> onSale = carRepository.findBySalesStatus(SalesStatus.ON);
        List<Car> readySale = carRepository.findBySalesStatus(SalesStatus.READY);
        List<Car> stopSale = carRepository.findBySalesStatus(SalesStatus.STOP);
        return new CarStatusInfoDto(all.size(), readySale.size(), onSale.size(), stopSale.size());
    }

    public List<CarDto.GetInfo> findByCarCondition(CarSearchConditionDto condition){
        CarSearchCondition carSearchCondition = condition.toSearchCondition();
//        List<Car> all = carRepository.findAllWithCategoryAndAssessor();
        List<Car> bySearchCondition = carRepository.findBySearchCondition(carSearchCondition);
        return bySearchCondition.stream().map(c -> new CarDto.GetInfo(c.getId(),
                            c.getSalesStatus(),
                            c.getImportStatus(),
                            c.getName(),
                            c.getCarNumber(),
                            c.getVehicleType(),
                            c.getSeater(),
                            c.getModelYear(),
                            c.getMileage(),
                            c.getColor(),
                            c.getFuel(),
                            c.getManufacturer(),
                            c.getModel(),
                            c.getDetailModel(),
                            c.getTransmission(),
                            c.isAccident(),
                            c.getDriveType(),
                            c.getCategories().getName(),
                            c.getAssessor().getName(),
                            c.getAssessor().getDirectShop(),
                            c.getStockQuantity(),
                            c.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                            .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCar(Long id) {
        Car car = carRepository.findOne(id);
        if(!car.getSalesStatus().equals(SalesStatus.STOP)){
            throw new IllegalStateException("판매중인 상품입니다.");
        }

        inspectionRecordRepository.delete(id); //연관관계 제거
        insuranceHistoryRepository.delete(id); //연관관계 제거
        carRepository.delete(car); //차량제거
    }
}
