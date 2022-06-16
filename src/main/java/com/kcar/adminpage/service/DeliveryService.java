package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.Delivery;
import com.kcar.adminpage.controller.dto.delivery.DeliveryDto;
import com.kcar.adminpage.controller.dto.delivery.DeliverySearchConditionDto;
import com.kcar.adminpage.repository.DeliveryRepository;
import com.kcar.adminpage.repository.condition.DeliverySearchCondition;
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

    public List<DeliveryDto.GetInfo> findAllDelivery(DeliverySearchConditionDto deliverySearchConditionDto) {
        DeliverySearchCondition condition = deliverySearchConditionDto.toSearchCondition(); //상태객체 변환

        List<Delivery> all = deliveryRepository.findBySearchCondition(condition);
        List<DeliveryDto.GetInfo> collect = all.stream()
                .map(d -> new DeliveryDto.GetInfo(d.getId(),
                        d.getReceiver(),
                        d.getNumber(),
                        d.getDeliveryStatus(),
                        d.getHopeDeliveryDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        d.getDeliveryCompleteDate(),
                        d.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        d.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        d.getAddress()))
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional
    public void updateDelivery(DeliveryDto.UpdateInfo updateInfo) {
        if (updateInfo.getId() != null) {
            Long id = Long.parseLong(updateInfo.getId());
            Delivery delivery = deliveryRepository.findOne(id);
            delivery.changeDeliveryInfo(updateInfo.getReceiver(),
                    updateInfo.getDeliveryStatus());
        }
    }
}
