package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.controller.dto.IdDto;
import com.kcar.adminpage.controller.dto.ResponseDto;
import com.kcar.adminpage.controller.dto.cardto.CarDto;
import com.kcar.adminpage.controller.dto.Result;
import com.kcar.adminpage.controller.dto.cardto.CarSearchConditionDto;
import com.kcar.adminpage.controller.dto.cardto.CarStatusInfoDto;
import com.kcar.adminpage.service.CarService;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/api/car-home") //모든차량 조회
    public CarStatusInfoDto carHome() {
        return carService.findCarBySaleStatus();
    }

    @PutMapping("/api/cars") //모든차량 조회
    public Result<List<CarDto.GetInfo>> carList(@RequestBody CarSearchConditionDto conditionDto) {
        List<CarDto.GetInfo> allCarInfo = carService.findByCarCondition(conditionDto);
        return new Result<List<CarDto.GetInfo>>(allCarInfo.size(), allCarInfo);
    }

    @PostMapping("/api/car-save") //차량 등록 -> return httpResponse... 작업요함
    public ResponseDto<String> carSave(@Validated @RequestBody CarDto.PostInfo info) {
        carService.saveCar(info);
        return new ResponseDto<String>(HttpStatus.OK.value(), "차량등록 완료");
    }

    //선택삭제
    @DeleteMapping("/api/cars-delete")
    public ResponseEntity<ResponseDto<String>> deleteCar(@RequestBody IdDto id) {
        carService.deleteCar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/car-home"));
        ResponseDto<String> responseBody = new ResponseDto<>(HttpStatus.OK.value(), "삭제 완료");
        return new ResponseEntity<ResponseDto<String>>(responseBody ,headers, HttpStatus.OK);
    }
}

