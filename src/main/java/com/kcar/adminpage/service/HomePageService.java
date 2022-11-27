package com.kcar.adminpage.service;

import com.kcar.adminpage.controller.dto.homedto.RecentCreateCarDto;
import com.kcar.adminpage.controller.dto.homedto.RecentReviewListDto;
import com.kcar.adminpage.controller.dto.userhomedto.RecentCarDto;
import com.kcar.adminpage.controller.dto.userhomedto.RecentReviewDto;
import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.Review;
import com.kcar.adminpage.repository.CarRepository;
import com.kcar.adminpage.repository.PurchaseCostRepository;
import com.kcar.adminpage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomePageService {

    private final CarRepository carRepository;
    private final ReviewRepository reviewRepository;

    public List<RecentCarDto> findHomeWithCarListInfo(){

        List<Car> orderByDate = carRepository.findOrderByDateWithPurchase();
        List<RecentCarDto> recentCreateCarDtoList = orderByDate.stream().
                map(c -> new RecentCarDto(c.getId(),
                                          c.getName(),
                                          c.getImage(),
                                          c.getPurchaseCost().getCarPrice(),
                                          c.getModelYear(),
                                          c.getMileage(),
                                          c.getFuel()))
                .collect(Collectors.toList());

        return recentCreateCarDtoList;
    }


    public List<RecentReviewDto> findHomeWithReviewListInfo(){
        List<Review> reviewByRecentDate = reviewRepository.findReviewByRecentDate();
        List<RecentReviewDto> reviewListDto = reviewByRecentDate.stream().map(r -> new RecentReviewDto(r.getId(), r.getTitle(), r.getContent(),r.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                .collect(Collectors.toList());

        return reviewListDto;
    }
}
