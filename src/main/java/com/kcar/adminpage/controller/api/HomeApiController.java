package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.controller.dto.InquiryClientDto;
import com.kcar.adminpage.controller.dto.InquiryItemDto;
import com.kcar.adminpage.controller.dto.InquiryOfferDto;
import com.kcar.adminpage.controller.dto.Result;
import com.kcar.adminpage.controller.dto.homedto.HomeDto;
import com.kcar.adminpage.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final HomeService homeService;

    @GetMapping("/api/home")
    public HomeDto homeInfo(){
        return homeService.findHomeInfo();
    }

    @GetMapping("/api/home-inquiry-offer")
    public Result<List<InquiryOfferDto>> inquiryOffer(){
        List<InquiryOfferDto> inquiryInfo = homeService.findInquiryOfferInfo();
        return new Result<List<InquiryOfferDto>>(inquiryInfo.size(), inquiryInfo);
    }

    @GetMapping("/api/home-inquiry-item")
    public Result<List<InquiryItemDto>> inquiryItem(){
        List<InquiryItemDto> inquiryInfo = homeService.findInquiryItemInfo();
        return new Result<List<InquiryItemDto>>(inquiryInfo.size(), inquiryInfo);
    }

    @GetMapping("/api/home-inquiry-client")
    public Result<List<InquiryClientDto>> inquiryClient(){
        List<InquiryClientDto> inquiryInfo = homeService.findInquiryClientInfo();
        return new Result<List<InquiryClientDto>>(inquiryInfo.size(), inquiryInfo);
    }
}
