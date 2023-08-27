package com.xinerji.currency.model.mapper;

import com.xinerji.currency.config.BaseMapperConfig;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserRequest;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public abstract class UserMapper {

    public static final UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    public abstract UserRequestDto mapRequestToRequestDto(UserRequest userRequest);

    public abstract Users mapRequestDtoToEntity(UserRequestDto userRequestDto);
    public abstract UserResponseDto mapEntityToResponseDto(Users entity);

    public abstract UserResponse mapResponseDtoToResponse(UserResponseDto responseDto);
}
