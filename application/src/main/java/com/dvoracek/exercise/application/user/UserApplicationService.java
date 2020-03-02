package com.dvoracek.exercise.application.user;

import com.dvoracek.exercise.domain.user.User;
import com.dvoracek.exercise.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserApplicationService {

    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userRepository.save(new User().setEmail(createUserDto.getEmail()));
        return UserDto.toUserDto(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserDto::toUserDto).collect(Collectors.toList());
    }
}
