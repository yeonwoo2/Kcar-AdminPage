package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.dto.CarDto;
import com.kcar.adminpage.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional // 차량 등록
    public void saveCar(Car car){
        carRepository.save(car);
    }

    @Transactional // 차량 업데이트
    public void updateCar(Long carId, CarDto carDto){
        Car findCar = carRepository.findOne(carId);
//        findCar.changeCarInfo(carDto.get)
    }



}
