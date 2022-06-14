package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.IdDto;
import com.kcar.adminpage.dto.cardto.CarDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.dto.cardto.CarSearchConditionDto;
import com.kcar.adminpage.dto.cardto.CarStatusInfoDto;
import com.kcar.adminpage.service.CarService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/api/car-home") //모든차량 조회
    public CarStatusInfoDto carHome() {
        return carService.findCarBySaleStatus();
    }

    @GetMapping("/api/cars") //모든차량 조회
    public Result carList(@RequestBody CarSearchConditionDto conditionDto) {
        List<CarDto.GetInfo> allCarInfo = carService.findByCarCondition(conditionDto);
        return new Result(allCarInfo.size(), allCarInfo);
    }

//    @GetMapping("/api/cars") //삭제
//    public Result carList(@RequestBody CarSearchConditionDto conditionDto) {
//        List<CarDto.GetInfo> allCarInfo = carService.findByCarCondition(conditionDto);
//        return new Result(allCarInfo.size(), allCarInfo);
//    }
//
//    @GetMapping("/api/cars") //수정항목 저장
//    public Result carList(@RequestBody CarSearchConditionDto conditionDto) {
//        List<CarDto.GetInfo> allCarInfo = carService.findByCarCondition(conditionDto);
//        return new Result(allCarInfo.size(), allCarInfo);
//    }

    @PostMapping("/api/car-save") //차량 등록 -> return httpResponse... 작업요함
    public void carSave(@RequestBody CarDto.PostInfo info) {
        carService.saveCar(info);
    }

    @PutMapping("/api/cars/{id}") //차량 정보 업데이트 -> return httpResponse... 작업요함
    public void updateCarInfo(@PathVariable("id") Long id,
                              @RequestBody CarDto.UpdateInfo request) {
        carService.updateCar(id, request);
    }

    @DeleteMapping("/api/cars")
    public void deleteCar(@RequestBody IdDto id) {
        carService.deleteCar(id);
    }
}

