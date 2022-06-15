package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.IdDto;
import com.kcar.adminpage.dto.cardto.CarDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.dto.cardto.CarSearchConditionDto;
import com.kcar.adminpage.dto.cardto.CarStatusInfoDto;
import com.kcar.adminpage.service.CarService;
import lombok.*;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/api/car-save") //차량 등록 -> return httpResponse... 작업요함
    public void carSave(@Validated @RequestBody CarDto.PostInfo info) {
        carService.saveCar(info);

    }

    //선택삭제
    @DeleteMapping("/api/cars-delete")
    public void deleteCar(@RequestBody IdDto id) {
        carService.deleteCar(id);
    }
}

