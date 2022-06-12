package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.OrderCarDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.service.OrderCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderCarController {

    private final OrderCarService orderCarService;

    @GetMapping("/api/orders")
    public Result orderList(){
        List<OrderCarDto.GetInfo> orders = orderCarService.findAllOrders();
        return new Result(orders.size(), orders);
    }

}
