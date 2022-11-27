package com.kcar.adminpage.controller.dto.userhomedto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecentCarDto {

    private Long id;
    private String carName;
    private String imageURL;
    private Integer cost;
    private String modelYear;
    private String mileage;
    private String fuel;
}
