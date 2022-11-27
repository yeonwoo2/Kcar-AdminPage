package com.kcar.adminpage.controller;

import com.kcar.adminpage.controller.dto.cardto.CarDetailDto;
import com.kcar.adminpage.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/car")
    public String car(@RequestParam("id") String id, Model model) {
        Long carId = Long.parseLong(id);
        CarDetailDto findCarDto = carService.findCarByOne(carId);
        model.addAttribute("carDetail", findCarDto);
        return "car";
    }
}
