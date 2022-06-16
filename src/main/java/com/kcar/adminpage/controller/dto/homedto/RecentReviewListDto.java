package com.kcar.adminpage.controller.dto.homedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecentReviewListDto {
    private Long id;
    private String title;
    private String createDate;
}
