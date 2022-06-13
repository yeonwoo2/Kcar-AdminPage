package com.kcar.adminpage.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserCountDto {
    private int totalUser;
    private int newUser;
    private int subUser;
    private int normalUser;
}
