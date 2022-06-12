package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.DeliveryDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/api/deliveries") //order_car 테스트
    public Result orderList(){
        List<DeliveryDto.GetInfo> allDelivery = deliveryService.findAllDelivery();
        return new Result(allDelivery.size(), allDelivery);
    }
}
