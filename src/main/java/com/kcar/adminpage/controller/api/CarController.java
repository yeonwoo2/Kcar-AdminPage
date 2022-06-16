package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.controller.dto.IdDto;
import com.kcar.adminpage.controller.dto.ResponseDto;
import com.kcar.adminpage.controller.dto.cardto.CarDto;
import com.kcar.adminpage.controller.dto.Result;
import com.kcar.adminpage.controller.dto.cardto.CarSearchConditionDto;
import com.kcar.adminpage.controller.dto.cardto.CarStatusInfoDto;
import com.kcar.adminpage.service.CarService;
import lombok.*;
import org.springframework.http.HttpStatus;
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
    public Result<List<CarDto.GetInfo>> carList(@RequestBody CarSearchConditionDto conditionDto) {
        List<CarDto.GetInfo> allCarInfo = carService.findByCarCondition(conditionDto);
        return new Result<List<CarDto.GetInfo>>(allCarInfo.size(), allCarInfo);
    }

    @PostMapping("/api/car-save") //차량 등록 -> return httpResponse... 작업요함
    public ResponseDto<Integer> carSave(@Validated @RequestBody CarDto.PostInfo info) {
        carService.saveCar(info);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //선택삭제
    @DeleteMapping("/api/cars-delete")
    public ResponseDto<Integer> deleteCar(@RequestBody IdDto id) {
        carService.deleteCar(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

