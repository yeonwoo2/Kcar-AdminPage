package com.kcar.adminpage.controller.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class InquiryDto {
    private int offerInquiry;
    private int itemInquiry;
    private int clientInquiry;
}
