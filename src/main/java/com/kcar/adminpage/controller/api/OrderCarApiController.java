package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.controller.dto.ordercardto.OrderCarDto;
import com.kcar.adminpage.controller.dto.Result;
import com.kcar.adminpage.controller.dto.ordercardto.OrderCarSearchConditionDto;
import com.kcar.adminpage.service.OrderCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderCarApiController {

    private final OrderCarService orderCarService;

    @PutMapping("/api/orders")
    public Result orderList(@RequestBody OrderCarSearchConditionDto conditionDto){
        List<OrderCarDto> orders = orderCarService.findAllOrders(conditionDto);
        return new Result(orders.size(), orders);
    }

}
