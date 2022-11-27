package com.kcar.adminpage.controller.api;

import com.kcar.adminpage.controller.dto.ResponseDto;
import com.kcar.adminpage.controller.dto.Result;
import com.kcar.adminpage.controller.dto.userdto.UserConditionDto;
import com.kcar.adminpage.controller.dto.userdto.UserDto;
import com.kcar.adminpage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/users")
    public Result<List<UserDto.GetInfo>> userList(@RequestBody UserConditionDto userConditionDto){
        List<UserDto.GetInfo> allUser = userService.findAllUser(userConditionDto); //dto 사용 권장
        return new Result<List<UserDto.GetInfo>>(allUser.size(), allUser);
    }

    @PutMapping("/api/users-update") //차량 정보 업데이트 -> return httpResponse... 작업요함
    public ResponseDto<Integer> updateUserAuth(@RequestBody UserDto.UpdateInfo request){
        userService.updateUserAuth(request);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
