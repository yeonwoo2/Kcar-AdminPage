package com.kcar.adminpage.dto;

import com.kcar.adminpage.domain.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GetInfo{
        private Long userId;
        private String name;
        private String email;
        private String phoneNumber;
        private Authority authority;
    }

    @Getter @Setter
    public static class UpdateInfo {
        private Authority authority;
    }
}
