package com.xinerji.currency.model.mapper;

import com.xinerji.currency.config.BaseMapperConfig;
import com.xinerji.currency.model.dto.auth.AuthResponse;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public abstract class LoginMapper {

    public static final LoginMapper INSTANCE= Mappers.getMapper(LoginMapper.class);
    public abstract AuthRequestDto mapRequestToRequestDto(AuthRequest authRequest);
    public abstract AuthResponse mapResposeDtoToResponse(AuthResponseDto responseDto);
}
