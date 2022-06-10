package com.kcar.adminpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> {
    private int cnt;
    private T data;
}
