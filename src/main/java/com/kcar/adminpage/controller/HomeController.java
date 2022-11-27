package com.kcar.adminpage.controller;

import com.kcar.adminpage.controller.dto.userhomedto.RecentCarDto;
import com.kcar.adminpage.controller.dto.userhomedto.RecentReviewDto;
import com.kcar.adminpage.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomePageService homePageService;

    @GetMapping("/")
    public String home(Model model) {

        List<RecentCarDto> homeWithCarListInfo = homePageService.findHomeWithCarListInfo();
        List<RecentReviewDto> homeWithReviewListInfo = homePageService.findHomeWithReviewListInfo();

        model.addAttribute("cars", homeWithCarListInfo);
        model.addAttribute("reviews", homeWithReviewListInfo);

        return "home";
    }

}
