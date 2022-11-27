package com.kcar.adminpage.controller.dto.userhomedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecentReviewDto {
    private Long id;
    private String title;
    private String content;
    private String createDate;
}
