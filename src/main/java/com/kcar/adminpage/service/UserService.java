package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.User;
import com.kcar.adminpage.dto.userdto.UserConditionDto;
import com.kcar.adminpage.dto.userdto.UserDto;
import com.kcar.adminpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto.GetInfo> findAllUser(UserConditionDto conditionDto) {
        List<User> users = userRepository.findBySearchCondition(conditionDto);
        return users.stream()
                .map(u -> new UserDto.GetInfo(u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getNumber(),
                        u.getAuthority())).collect(Collectors.toList());
    }

    @Transactional
    public void updateUserAuth(UserDto.UpdateInfo request) {
        if(request.getId() != null){
            Long id = Long.parseLong(request.getId());
            User user = userRepository.findOne(id);
            user.changeUserAuth(request.getAuthority());
        }
    }
}
