package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.CarDto;
import com.kcar.adminpage.service.CarService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarApiController {

    private final CarService carService;

    @GetMapping("/api/cars") //모든차량 조회
    public Result carList(){
        List<CarDto.GetInfo> allCarInfo = carService.findAllCarInfo(); //dto 사용 권장
        return new Result(allCarInfo.size(), allCarInfo);
    }

//    @GetMapping("/api/car/{id}") //차량 한대 조회 -> 필요?
//    public Car oneCar(@PathVariable("id") Long id){
//        return carRepository.findOne(id);
//    }

    @PostMapping("/api/car-save") //차량 등록 -> return httpResponse... 작업요함
    public void carSave(@RequestBody CarDto.PostInfo info){
        carService.saveCar(info);
    }

    @PutMapping("/api/cars/{id}") //차량 정보 업데이트 -> return httpResponse... 작업요함
    public void updateCarInfo(@PathVariable("id") Long id,
                              @RequestBody CarDto.UpdateInfo request){
        carService.updateCar(id, request);
    }

    @Getter @Setter
    @AllArgsConstructor
    static class Result<T> {
        private int cnt;
        private T data;
    }
}
