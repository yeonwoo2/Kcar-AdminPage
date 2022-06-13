package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.homedto.HomeDto;
import com.kcar.adminpage.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/api/home")
    public HomeDto homeInfo(){
        return homeService.findHomeInfo();
    }
}
