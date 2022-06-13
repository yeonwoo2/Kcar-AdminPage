package com.kcar.adminpage.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class RecentCreateCarDto {
    private String importStatus;
    private String carName;
    private String createDate;
}
