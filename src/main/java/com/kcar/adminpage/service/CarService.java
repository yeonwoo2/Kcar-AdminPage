package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Assessor;
import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.Category;
import com.kcar.adminpage.dto.requestdto.CarDto;
import com.kcar.adminpage.repository.AssessorRepository;
import com.kcar.adminpage.repository.CarRepository;
import com.kcar.adminpage.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final AssessorRepository assessorRepository;
    private final CategoryRepository categoryRepository;

    @Transactional // 차량 등록
    public void saveCar(CarDto.Info info, Long categoryId, Long assessorId){

        //db 조회
        Assessor assessor = assessorRepository.findOne(assessorId);
        Category category = categoryRepository.findOne(categoryId);

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
    public void updateCar(Long carId, CarDto.UpdateInfo updateInfo){
        Car findCar = carRepository.findOne(carId);
        findCar.changeCarInfo(updateInfo.getImportStatus(), updateInfo.getSalesStatus(), updateInfo.isAccident(), updateInfo.getStockQuantity());
    }

    //검색
    public List<Car> findAllCarInfo(){
        return carRepository.findAll();
    }

}
