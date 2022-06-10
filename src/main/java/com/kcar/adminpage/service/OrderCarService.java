package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.dto.OrderCarDto;
import com.kcar.adminpage.repository.OrderCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderCarService {

    private final OrderCarRepository orderCarRepository;

    public List<OrderCarDto.GetInfo> findAllOrders (){
        List<OrderCar> allWithOrderFormAndCar = orderCarRepository.findAllWithOrderFormAndCar();
        return allWithOrderFormAndCar.stream()
                .map(o -> new OrderCarDto.GetInfo(o.getId(),
                        o.getDeadlinePaymentDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                        o.getOrderStatus(),
                        o.getCar().getName(),
                        o.getOrderForm().getAccountNumber(),
                        o.getOrderForm().getBank(),
                        o.getOrderForm().getHolderRight(),
                        o.getOrderForm().getNumber(),
                        o.getClaimStatus(),
                        o.getPurchaseStatus()))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public void saveOrder(OrderCarDto.PostInfo info){
//        OrderCar.createOrderCar(info.getClaimStatus(), info.getPurchaseStatus(), info.getOrderStatus(), info.getOrder)
//    }
}
