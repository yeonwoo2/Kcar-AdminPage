package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.*;
import com.kcar.adminpage.domain.enums.SalesStatus;
import com.kcar.adminpage.controller.dto.IdDto;
import com.kcar.adminpage.controller.dto.cardto.CarDto;
import com.kcar.adminpage.controller.dto.cardto.CarSearchConditionDto;
import com.kcar.adminpage.controller.dto.cardto.CarStatusInfoDto;
import com.kcar.adminpage.handler.ex.CustomValidationException;
import com.kcar.adminpage.repository.*;
import com.kcar.adminpage.repository.condition.CarSearchCondition;
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

        Category category;
        Assessor assessor;

        try{
            category = categoryRepository.findByName(info.getCategoryName());// 단건 조회
            assessor = assessorRepository.findByEmployeeNumber(info.getAssessorEmployeeNumber());//단건 조회
        }catch (Exception e){
            throw new CustomValidationException("유효한 카테고리 또는 사번을 입력해주세요");
        }

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

    public CarStatusInfoDto findCarBySaleStatus(){
        List<Car> all = carRepository.findAll();
        List<Car> onSale = carRepository.findBySalesStatus(SalesStatus.ON);
        List<Car> readySale = carRepository.findBySalesStatus(SalesStatus.READY);
        List<Car> stopSale = carRepository.findBySalesStatus(SalesStatus.STOP);
        return new CarStatusInfoDto(all.size(), readySale.size(), onSale.size(), stopSale.size());
    }

    //필터검색
    public List<CarDto.GetInfo> findByCarCondition(CarSearchConditionDto condition){
        System.out.println("============= " + condition.getCarName());
        CarSearchCondition carSearchCondition = condition.toSearchCondition();
        List<Car> bySearchCondition = carRepository.findBySearchCondition(carSearchCondition);
        return bySearchCondition.stream().map(c -> new CarDto.GetInfo(c.getId(),
                            c.getSalesStatus(),
                            c.getImportStatus(),
                            c.getName(),
                            c.getCarNumber(),
                            c.getVehicleType(),
                            c.getSeater(),
                            c.getMileage(),
                            c.getColor(),
                            c.getFuel(),
                            c.getManufacturer(),
                            c.getModel(),
                            c.getDetailModel(),
                            c.getTransmission(),
                            c.getModelYear(),
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
    public void deleteCar(IdDto id) {

        try{
            carRepository.deleteByCarIdIn(id.getId()); //차량제거
            inspectionRecordRepository.deleteByIdIn(id.getId()); //연관관계 제거
            insuranceHistoryRepository.deleteByIdIn(id.getId()); //연관관계 제거
            purchaseCostRepository.deleteByIdIn(id.getId()); //연관관계 제거
        }catch (Exception e){
            throw new CustomValidationException("주문된 상품은 삭제할 수 없습니다.");
        }
    }
}
