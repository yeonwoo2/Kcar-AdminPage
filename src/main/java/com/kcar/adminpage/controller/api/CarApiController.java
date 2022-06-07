package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.dto.requestdto.CarDto;
import com.kcar.adminpage.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarApiController {

    private final CarService carService;

    @GetMapping("/api/allCar") //모든차량 조회
    public List<Car> carList(){
        return carService.findAllCarInfo();
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
}
