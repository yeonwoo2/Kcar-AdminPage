package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.dto.ordercardto.OrderCarDto;
import com.kcar.adminpage.dto.ordercardto.OrderCarSearchConditionDto;
import com.kcar.adminpage.repository.OrderCarRepository;
import com.kcar.adminpage.repository.condition.OrderCarSearchCondition;
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

    public List<OrderCarDto> findAllOrders (OrderCarSearchConditionDto conditionDto){
        OrderCarSearchCondition condition = conditionDto.toSearchCondition();
        List<OrderCar> orderCarBySearchCondition = orderCarRepository.findBySearchCondition(condition);
        return orderCarBySearchCondition.stream()
                .map(o -> new OrderCarDto(o.getId(),
                                o.getOrderForm().getHolderRight(),
                                o.getOrderForm().getNumber(),
                                o.getOrderForm().getBank(),
                                o.getOrderForm().getAccountNumber(),
                                o.getCar().getName(),
                                o.getOrderStatus(),
                                o.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // nullpointer 발생 -> string 필드로 변환
                                o.getDeadlinePaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                o.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                .collect(Collectors.toList());
    }

}
