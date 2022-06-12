package com.kcar.adminpage.service;

import com.kcar.adminpage.domain.User;
import com.kcar.adminpage.dto.UserDto;
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

    public List<UserDto.GetInfo> findAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> new UserDto.GetInfo(u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getNumber(),
                        u.getAuthority())).collect(Collectors.toList());
    }

    @Transactional
    public void updateUserAuth(Long id, UserDto.UpdateInfo request) {
        User user = userRepository.findOne(id);
        user.changeUserAuth(request.getAuthority());
    }
}
