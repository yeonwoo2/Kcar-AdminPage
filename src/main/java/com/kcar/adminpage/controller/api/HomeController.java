package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.InquiryClientDto;
import com.kcar.adminpage.dto.InquiryItemDto;
import com.kcar.adminpage.dto.InquiryOfferDto;
import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.dto.homedto.HomeDto;
import com.kcar.adminpage.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/api/home")
    public HomeDto homeInfo(){
        return homeService.findHomeInfo();
    }

    @GetMapping("/api/home-inquiry-offer")
    public Result inquiryOffer(){
        List<InquiryOfferDto> inquiryInfo = homeService.findInquiryOfferInfo();
        return new Result(inquiryInfo.size(), inquiryInfo);
    }

    @GetMapping("/api/home-inquiry-item")
    public Result inquiryItem(){
        List<InquiryItemDto> inquiryInfo = homeService.findInquiryItemInfo();
        return new Result(inquiryInfo.size(), inquiryInfo);
    }

    @GetMapping("/api/home-inquiry-client")
    public Result inquiryClient(){
        List<InquiryClientDto> inquiryInfo = homeService.findInquiryClientInfo();
        return new Result(inquiryInfo.size(), inquiryInfo);
    }
}
