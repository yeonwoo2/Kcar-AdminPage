package com.kcar.adminpage.dto.userdto;

import com.kcar.adminpage.domain.enums.Authority;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserConditionDto {
    private Authority authority;
    private String name;
    private int paging;
}
