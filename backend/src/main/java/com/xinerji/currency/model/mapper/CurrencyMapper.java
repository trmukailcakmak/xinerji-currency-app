package com.xinerji.currency.model.mapper;

import com.xinerji.currency.config.BaseMapperConfig;
import com.xinerji.currency.model.dto.currency.CurrencyRequest;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponse;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.dto.user.UserRequest;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public abstract class CurrencyMapper {

    public static final CurrencyMapper INSTANCE= Mappers.getMapper(CurrencyMapper.class);

    public abstract CurrencyRequestDto mapRequestToRequestDto(CurrencyRequest request);

    public abstract UserResponseDto mapEntityToResponseDto(Users entity);

    public abstract CurrencyResponse mapResponseDtoToResponse(CurrencyResponseDto responseDto);
}
