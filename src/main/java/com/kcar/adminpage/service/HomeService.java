package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.Delivery;
import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.domain.enums.DeliveryStatus;
import com.kcar.adminpage.domain.enums.SalesStatus;
import com.kcar.adminpage.dto.homedto.*;
import com.kcar.adminpage.repository.CarRepository;
import com.kcar.adminpage.repository.DeliveryRepository;
import com.kcar.adminpage.repository.OrderCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {

    private final OrderCarRepository orderCarRepository;
    private final DeliveryRepository deliveryRepository;
    private final CarRepository carRepository;

    public HomeDto findHomeInfo() {
        List<OrderCar> orderWait = orderCarRepository.findByOrderStatus("WAIT");
        List<OrderCar> orderComp = orderCarRepository.findByOrderStatus("COMP");
        List<OrderCar> orderCancel = orderCarRepository.findByOrderStatus("CANCEL");
        List<OrderCar> orderReturn = orderCarRepository.findByOrderStatus("RETURN");
        List<OrderCar> comp = orderCarRepository.findByOrderCompAndDate("COMP", LocalDateTime.now().minusHours(23)); //수정요함

        List<Delivery> ready = deliveryRepository.findByDeliveryStatus(DeliveryStatus.READY);
        List<Delivery> going = deliveryRepository.findByDeliveryStatus(DeliveryStatus.GOING);
        List<Delivery> deliveryComp = deliveryRepository.findByDeliveryStatus(DeliveryStatus.COMP);

        List<Car> onSale = carRepository.findBySalesStatus(SalesStatus.ON);
        List<Car> readySale = carRepository.findBySalesStatus(SalesStatus.READY);
        List<Car> StopSale = carRepository.findBySalesStatus(SalesStatus.STOP);
        List<Car> orderByDate = carRepository.findOrderByDate();

        List<RecentCreateCarDto> collect = orderByDate.stream().
                map(c -> new RecentCreateCarDto(c.getImportStatus(), c.getName(), c.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy.MM-dd HH:mm"))))
                .collect(Collectors.toList());

        OrderAndDeliveryInfo orderAndDeliveryInfo = new OrderAndDeliveryInfo(orderWait.size(),orderComp.size(), ready.size(), going.size(), deliveryComp.size());
        ClaimAndCalculate claimAndCalculate = new ClaimAndCalculate(orderCancel.size(), orderReturn.size(), comp.size());
        CarInfoDto carInfoDto = new CarInfoDto(onSale.size(), readySale.size(), StopSale.size());


        return new HomeDto(orderAndDeliveryInfo, claimAndCalculate, carInfoDto, collect) ;
    }
}
