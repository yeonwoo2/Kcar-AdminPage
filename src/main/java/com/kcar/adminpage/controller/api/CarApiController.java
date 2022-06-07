package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.dto.requestdto.CarDto;
import com.kcar.adminpage.repository.CarRepository;
import com.kcar.adminpage.service.CarService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarApiController {

    private final CarService carService;
    private final CarRepository carRepository;

    @GetMapping("/api/allCar") //모든차량 조회
    public Result carList(){
        List<Car> allCarInfo = carService.findAllCarInfo(); //dto 사용 권장
//        List<CarDto.responseInfo> collect = allCarInfo.stream()
//                .map(c -> new CarDto.responseInfo(c.getName(), c.getCarNumber()))
//                .collect(Collectors.toList());
        return new Result(allCarInfo.size(), allCarInfo);
    }

    @GetMapping("/api/car/{id}") //차량 한대 조회
    public Car oneCar(@PathVariable("id") Long id){
        return carRepository.findOne(id);
    }

    @PostMapping("/api/carSave") //차량 등록
    public void carSave(@RequestBody CarDto.Info info,
                        @RequestParam("categoryId") Long categoryId,
                        @RequestParam("assessorId") Long assessorId){
        carService.saveCar(info, categoryId, assessorId);
    }

    @PutMapping("/api/cars/{id}")
    public void updateCarInfo(
            @PathVariable("id") Long id,
            @RequestBody CarDto.UpdateInfo request){

        carService.updateCar(id, request);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int cnt;
        private T data;
    }
}
