package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.DeliveryDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/api/deliveries") //order_car 테스트
    public Result deliveryList(){
        List<DeliveryDto.GetInfo> allDelivery = deliveryService.findAllDelivery();
        return new Result(allDelivery.size(), allDelivery);
    }

    @PutMapping("/api/deliveries/{id}") //차량 정보 업데이트 -> return httpResponse... 작업요함
    public void updateDeliveryInfo(@PathVariable("id") Long id,
                              @RequestBody DeliveryDto.UpdateInfo request){
        deliveryService.updateDelivery(id ,request);
    }
}