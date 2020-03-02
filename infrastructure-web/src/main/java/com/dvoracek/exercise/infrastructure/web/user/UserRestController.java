package com.dvoracek.exercise.infrastructure.web.user;

import com.dvoracek.exercise.application.user.CreateUserDto;
import com.dvoracek.exercise.application.user.UserApplicationService;
import com.dvoracek.exercise.application.user.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final UserApplicationService userApplicationService;

    public UserRestController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto creteUser(@RequestBody @Validated CreateUserDto createUserDto) throws JsonProcessingException {
        LOGGER.debug("Received Http.POST /api/users : {}", new ObjectMapper().writeValueAsString(createUserDto));
        return this.userApplicationService.createUser(createUserDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers()  {
        LOGGER.debug("Received Http.GET /api/users");
        return this.userApplicationService.getAll();
    }
}
