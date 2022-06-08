package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Assessor;
import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.Category;
import com.kcar.adminpage.dto.requestdto.RequestCarDto;
import com.kcar.adminpage.dto.responsedto.ResponseCarDto;
import com.kcar.adminpage.repository.AssessorRepository;
import com.kcar.adminpage.repository.CarRepository;
import com.kcar.adminpage.repository.CategoryRepository;
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

    @Transactional // 차량 등록
    public void saveCar(RequestCarDto.PostInfo info){

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
        carRepository.save(car);
    }

    @Transactional // 차량 업데이트
    public void updateCar(Long carId, RequestCarDto.UpdateInfo updateInfo){
        Car findCar = carRepository.findOne(carId);
        findCar.changeCarInfo(updateInfo.getImportStatus(), updateInfo.getSalesStatus(), updateInfo.isAccident(), updateInfo.getStockQuantity());
    }

    //모든차량 검색
    public List<ResponseCarDto.GetInfo> findAllCarInfo(){
        List<Car> carList = carRepository.findAllWithCategoryAndAssessor();
        return carList.stream()
                .map(c -> new ResponseCarDto.GetInfo(c.getName(),
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

}
