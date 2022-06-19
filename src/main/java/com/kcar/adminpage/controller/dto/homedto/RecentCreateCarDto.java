package com.kcar.adminpage.controller.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class RecentCreateCarDto {
    private Long id;
    private String importStatus;
    private String carName;
    private String createDate;
}
