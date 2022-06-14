package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.*;
import com.kcar.adminpage.domain.enums.AnswerType;
import com.kcar.adminpage.domain.enums.Authority;
import com.kcar.adminpage.domain.enums.DeliveryStatus;
import com.kcar.adminpage.domain.enums.SalesStatus;
import com.kcar.adminpage.dto.InquiryClientDto;
import com.kcar.adminpage.dto.InquiryItemDto;
import com.kcar.adminpage.dto.InquiryOfferDto;
import com.kcar.adminpage.dto.homedto.*;
import com.kcar.adminpage.repository.*;
import com.kcar.adminpage.util.LocalDateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {

    private final OrderCarRepository orderCarRepository;
    private final DeliveryRepository deliveryRepository;
    private final CarRepository carRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;
    private LocalDateParser dateParser = new LocalDateParser(LocalDateTime.now()); //날짜 변환 util

    public HomeDto findHomeInfo() {
        List<StatisticsDto> statisticsDtoList = new ArrayList<>();
        List<InquiryOfferDto> inquiryOfferDtoList = new ArrayList<>();

        List<OrderCar> orderWait = orderCarRepository.findByOrderStatus("WAIT");
        List<OrderCar> orderComp = orderCarRepository.findByOrderStatus("COMP");
        List<OrderCar> orderCancel = orderCarRepository.findByOrderStatus("CANCEL");
        List<OrderCar> orderReturn = orderCarRepository.findByOrderStatus("RETURN");
        List<OrderCar> comp = orderCarRepository.findByOrderCompAndDate("COMP", dateParser.startDate(), dateParser.endDate()); //수정요함

        //이거 맞아?
        for(int i=0; i<30; i++){
            List<OrderCar> compOrder = orderCarRepository.findByOrderCompAndDateCount("COMP", dateParser.startDate(), dateParser.endDate(), i);
            statisticsDtoList.add(new StatisticsDto(LocalDateTime.now().minusDays(i).format(DateTimeFormatter.ofPattern("MM.dd")), compOrder.size()));
        }

        List<Delivery> ready = deliveryRepository.findByDeliveryStatus(DeliveryStatus.READY);
        List<Delivery> going = deliveryRepository.findByDeliveryStatus(DeliveryStatus.GOING);
        List<Delivery> deliveryComp = deliveryRepository.findByDeliveryStatus(DeliveryStatus.COMP);

        List<Car> onSale = carRepository.findBySalesStatus(SalesStatus.ON);
        List<Car> readySale = carRepository.findBySalesStatus(SalesStatus.READY);
        List<Car> StopSale = carRepository.findBySalesStatus(SalesStatus.STOP);

        List<Car> orderByDate = carRepository.findOrderByDate();
        List<RecentCreateCarDto> recentCreateCarDtoList = orderByDate.stream().
                map(c -> new RecentCreateCarDto(c.getImportStatus(), c.getName(), c.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                .collect(Collectors.toList());

        List<Review> allReview = reviewRepository.findAll(); //총 후기수
        List<Review> todayReviewList = reviewRepository.findByDate(dateParser.startDate(), dateParser.endDate()); //오늘 작성된 후기
        List<Review> reviewByRecentDate = reviewRepository.findReviewByRecentDate();
        List<RecentReviewListDto> reviewListDto = reviewByRecentDate.stream().map(r -> new RecentReviewListDto(r.getId(), r.getTitle(), r.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                .collect(Collectors.toList());

        List<User> allUser = userRepository.findAll();
        List<User> recentUser = userRepository.findByDate(dateParser.startDate(), dateParser.endDate());
        List<User> normalUser = userRepository.findByAuthority(Authority.USER);
        List<User> subsUser = userRepository.findByAuthority(Authority.SUBS);

        for(int i=0; i<24; i++){
            List<Question> questions = inquiryRepository.findByDateAndAnswerType(AnswerType.OFFER, dateParser.startDate(), i);
            inquiryOfferDtoList.add(new InquiryOfferDto(String.valueOf(i), questions.size()));
        }

        OrderAndDeliveryInfoDto orderAndDeliveryInfoDto = new OrderAndDeliveryInfoDto(orderWait.size(),orderComp.size(), ready.size(), going.size(), deliveryComp.size());
        ClaimAndCalculate claimAndCalculate = new ClaimAndCalculate(orderCancel.size(), orderReturn.size(), comp.size());
        CarInfoDto carInfoDto = new CarInfoDto(onSale.size(), readySale.size(), StopSale.size());
        ReviewDto reviewDto = new ReviewDto(allReview.size(), todayReviewList.size());
        UserCountDto userCountDto = new UserCountDto(allUser.size(), recentUser.size(), normalUser.size(), subsUser.size());

        return new HomeDto(orderAndDeliveryInfoDto, claimAndCalculate, carInfoDto, recentCreateCarDtoList, reviewDto, reviewListDto, userCountDto,statisticsDtoList, inquiryOfferDtoList);
    }

    public List<InquiryOfferDto> findInquiryOfferInfo(){
        List<InquiryOfferDto> inquiryOfferDtoList = new ArrayList<>();

        for(int i=0; i<24; i++){
            List<Question> questions = inquiryRepository.findByDateAndAnswerType(AnswerType.OFFER, dateParser.startDate(), i);
            inquiryOfferDtoList.add(new InquiryOfferDto(String.valueOf(i), questions.size()));
        }
        return inquiryOfferDtoList;
    }

    public List<InquiryItemDto> findInquiryItemInfo(){
        List<InquiryItemDto> inquiryOfferDtoList = new ArrayList<>();

        for(int i=0; i<24; i++){
            List<Question> questions = inquiryRepository.findByDateAndAnswerType(AnswerType.ITEM, dateParser.startDate(), i);
            inquiryOfferDtoList.add(new InquiryItemDto(String.valueOf(i), questions.size()));
        }
        return inquiryOfferDtoList;
    }

    public List<InquiryClientDto> findInquiryClientInfo(){
        List<InquiryClientDto> inquiryClientDtoList = new ArrayList<>();

        for(int i=0; i<24; i++){
            List<Question> questions = inquiryRepository.findByDateAndAnswerType(AnswerType.CLIENT, dateParser.startDate(), i);
            inquiryClientDtoList.add(new InquiryClientDto(String.valueOf(i), questions.size()));
        }
        return inquiryClientDtoList;
    }
}
