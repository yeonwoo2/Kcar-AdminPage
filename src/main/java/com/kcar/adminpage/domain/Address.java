package com.kcar.adminpage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String zipCode; // 우편번호
    private String city; // 주소
    private String street; // 상세주소

    public Address(String zipCode, String city, String street){
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
    }
}
