package com.xinerji.currency.controllers.impl;

import com.xinerji.currency.controllers.UserController;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.mapper.UserMapper;
import com.xinerji.currency.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public ResponseEntity<UserResponse> getUserInfo(String key) {
        UserResponseDto responseDto = userService.getUserInfo(key);
        UserResponse userResponse = userMapper.mapResponseDtoToResponse(responseDto);
        return ResponseEntity.ok(userResponse);
    }
    @Override
    public ResponseEntity<UserResponse> findUserById(Long id) {
        UserResponseDto responseDto = userService.findUserById(id);
        UserResponse response = userMapper.mapResponseDtoToResponse(responseDto);
        return ResponseEntity.ok(response);

    }

}
