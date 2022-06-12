package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.domain.OrderForm;
import com.kcar.adminpage.domain.PurchaseCost;
import com.kcar.adminpage.dto.OrderCarDto;
import com.kcar.adminpage.repository.CarRepository;
import com.kcar.adminpage.repository.OrderCarRepository;
import com.kcar.adminpage.repository.OrderFormRepository;
import com.kcar.adminpage.repository.PurchaseCostRepository;
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
                                o.getOrderForm().getHolderRight(),
                                o.getOrderForm().getNumber(),
                                o.getOrderForm().getBank(),
                                o.getOrderForm().getAccountNumber(),
                                o.getCar().getName(),
                                o.getOrderStatus(),
                                o.getPaymentDate(), // nullpointer 발생 -> string 필드로 변환
                                o.getDeadlinePaymentDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                                o.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))))
                .collect(Collectors.toList());
    }

    /**
     * 테스트 작성
     */
//    @Transactional
//    public void saveOrder(OrderCarDto.PostInfo info){
//        Car car = carRepository.findOne(1L);
//        OrderForm orderForm = orderFormRepository.findOne(1L);
//
//        OrderCar orderCar = OrderCar.createOrderCar(info.getOrderStatus(), orderForm, car);
//        orderCarRepository.save(orderCar);
//    }
}
