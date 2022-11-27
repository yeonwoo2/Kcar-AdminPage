package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.controller.dto.ResponseDto;
import com.kcar.adminpage.controller.dto.delivery.DeliveryDto;
import com.kcar.adminpage.controller.dto.Result;
import com.kcar.adminpage.controller.dto.delivery.DeliverySearchConditionDto;
import com.kcar.adminpage.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryApiController {

    private final DeliveryService deliveryService;

    @PutMapping("/api/deliveries")
    public Result<List<DeliveryDto.GetInfo>> deliveryList(@RequestBody DeliverySearchConditionDto searchConditionDto){
        List<DeliveryDto.GetInfo> allDelivery = deliveryService.findAllDelivery(searchConditionDto);
        return new Result<List<DeliveryDto.GetInfo>>(allDelivery.size(), allDelivery);
    }

    @PutMapping("/api/deliveries-update") //차량 정보 업데이트 -> return httpResponse... 작업요함
    public ResponseDto<Integer> updateDeliveryInfo(@RequestBody DeliveryDto.UpdateInfo request){
        deliveryService.updateDelivery(request);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
