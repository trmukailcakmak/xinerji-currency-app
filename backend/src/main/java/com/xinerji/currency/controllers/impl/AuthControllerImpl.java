package com.xinerji.currency.controllers.impl;

import com.xinerji.currency.controllers.AuthController;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponse;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.auth.AuthRequest;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserRequest;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.mapper.LoginMapper;
import com.xinerji.currency.model.mapper.UserMapper;
import com.xinerji.currency.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthControllerImpl.class);
    private final LoginMapper loginMapper = LoginMapper.INSTANCE;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserService userService;

    public AuthControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<AuthResponse> signIn(AuthRequest request){
        AuthRequestDto requestDto = loginMapper.mapRequestToRequestDto(request);
        AuthResponseDto authResponseDto = userService.login(requestDto);
        AuthResponse response = loginMapper.mapResposeDtoToResponse(authResponseDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserResponse> signUp(UserRequest request) {
        logger.info("AuthControllerImpl.signUp service request: " + request);
        UserRequestDto requestDto = userMapper.mapRequestToRequestDto(request);
        UserResponseDto responseDto = userService.register(requestDto);
        UserResponse response = userMapper.mapResponseDtoToResponse(responseDto);
        logger.info("AuthControllerImpl.signUp service response: " + response);
        return ResponseEntity.ok(response);
    }

}
