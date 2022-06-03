package com.kcar.adminpage.service;

import com.kcar.adminpage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입 추후작업
     */
}
