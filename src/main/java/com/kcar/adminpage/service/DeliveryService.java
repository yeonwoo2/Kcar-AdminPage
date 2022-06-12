package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Delivery;
import com.kcar.adminpage.dto.DeliveryDto;
import com.kcar.adminpage.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<DeliveryDto.GetInfo> findAllDelivery(){
        List<Delivery> all = deliveryRepository.findAll();
        return all.stream()
                .map(d -> new DeliveryDto.GetInfo(d.getId(),
                                                d.getReceiver(),
                                                d.getNumber(),
                                                d.getAddress(),
                                                d.getDeliveryStatus(),
                                                d.getHopeDeliveryDate(),
                                                d.getDeliveryCompleteDate(),
                                                d.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")), // nullpointer 발생 -> string 필드로 변환
                                                d.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))))
                .collect(Collectors.toList());
    }
}
