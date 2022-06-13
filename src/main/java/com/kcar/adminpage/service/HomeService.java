package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Delivery;
import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.domain.enums.DeliveryStatus;
import com.kcar.adminpage.dto.homedto.HomeDto;
import com.kcar.adminpage.dto.homedto.OrderAndDeliveryInfo;
import com.kcar.adminpage.repository.DeliveryRepository;
import com.kcar.adminpage.repository.OrderCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {

    private final OrderCarRepository orderCarRepository;
    private final DeliveryRepository deliveryRepository;

    public HomeDto findHomeInfo() {
        List<OrderCar> wait = orderCarRepository.findByOrderStatus("WAIT");
        List<OrderCar> orderComp = orderCarRepository.findByOrderStatus("COMP");

        List<Delivery> ready = deliveryRepository.findByDeliveryStatus(DeliveryStatus.READY);
        List<Delivery> going = deliveryRepository.findByDeliveryStatus(DeliveryStatus.GOING);
        List<Delivery> deliveryComp = deliveryRepository.findByDeliveryStatus(DeliveryStatus.COMP);

        OrderAndDeliveryInfo orderAndDeliveryInfo = new OrderAndDeliveryInfo(wait.size(),orderComp.size(), ready.size(), going.size(), deliveryComp.size());
        HomeDto homeDto = new HomeDto(orderAndDeliveryInfo);
        return homeDto;
    }
}
