package com.kcar.adminpage.domain;

import com.kcar.adminpage.enums.Authority;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 protected -> 무분별한 객체 생성 체크 기본생성자 막기
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String number;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority; // 유저 권한 [ADMIN, USER]


}
