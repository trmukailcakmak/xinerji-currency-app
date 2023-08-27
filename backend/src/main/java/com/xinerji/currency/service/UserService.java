package com.xinerji.currency.service;


import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponseDto;

public interface UserService {
    AuthResponseDto login(AuthRequestDto authRequestDto);
    UserResponseDto register(UserRequestDto userRequestDto);
    UserResponseDto registerAdmin(UserRequestDto userRequestDto);
    UserResponseDto findUserById(Long id);
    UserResponseDto getUserInfo(String key);
}
