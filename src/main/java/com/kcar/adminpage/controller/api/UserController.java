package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.dto.Result;
import com.kcar.adminpage.dto.userdto.UserConditionDto;
import com.kcar.adminpage.dto.userdto.UserDto;
import com.kcar.adminpage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public Result userList(@RequestBody UserConditionDto userConditionDto){
        List<UserDto.GetInfo> allUser = userService.findAllUser(userConditionDto); //dto 사용 권장
        return new Result(allUser.size(), allUser);
    }

    @PutMapping("/api/users") //차량 정보 업데이트 -> return httpResponse... 작업요함
    public void updateUserAuth(@RequestBody UserDto.UpdateInfo request){
        userService.updateUserAuth(request);
    }
}
