package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.DeliveryDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

//    @GetMapping("/api/home")
//    public Result homeInfo(){
//        List<DeliveryDto.GetInfo> allDelivery = homeService.findAllDelivery();
//        return new Result(allDelivery.size(), allDelivery);
//    }
}
