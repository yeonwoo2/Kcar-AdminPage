package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.OrderCarDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.service.OrderCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderCarController {

    private final OrderCarService orderCarService;

    @GetMapping("/api/orders") //order_car 테스트
    public Result orderList(){
        List<OrderCarDto.GetInfo> orders = orderCarService.findAllOrders();
        return new Result(orders.size(), orders);
    }

//    @PostMapping("/api/order-car-save")
//    public void orderCarSave(@RequestBody OrderCarDto.PostInfo info){
//        orderCarService.saveOrder(info);
//    }

}
