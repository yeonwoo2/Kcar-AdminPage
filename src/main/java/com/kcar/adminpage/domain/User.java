package com.kcar.adminpage.domain;

import com.kcar.adminpage.domain.enums.Authority;
import com.kcar.adminpage.domain.enums.SalesStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 protected -> 무분별한 객체 생성 체크 기본생성자 막기
public class User {

    @Id // 추후 회원 아이디로 변경할 예정
    private Long id;

    private String password;

    private String number;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority; // 유저 권한 [ADMIN, USER]

    @CreatedDate
    private LocalDateTime createDate;

    public void changeUserAuth(Authority authority){
        this.authority = authority;
    }
}
