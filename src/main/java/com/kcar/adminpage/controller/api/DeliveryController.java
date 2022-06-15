package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.delivery.DeliveryDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.dto.delivery.DeliverySearchConditionDto;
import com.kcar.adminpage.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/api/deliveries")
    public Result deliveryList(@RequestBody DeliverySearchConditionDto searchConditionDto){
        List<DeliveryDto.GetInfo> allDelivery = deliveryService.findAllDelivery(searchConditionDto);
        return new Result(allDelivery.size(), allDelivery);
    }

    @PutMapping("/api/deliveries") //차량 정보 업데이트 -> return httpResponse... 작업요함
    public void updateDeliveryInfo(@RequestBody DeliveryDto.UpdateInfo request){
        deliveryService.updateDelivery(request);
    }
}
