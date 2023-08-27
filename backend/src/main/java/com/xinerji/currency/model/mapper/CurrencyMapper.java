package com.xinerji.currency.model.mapper;

import com.xinerji.currency.config.BaseMapperConfig;
import com.xinerji.currency.model.dto.currency.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public abstract class CurrencyMapper {

    public static final CurrencyMapper INSTANCE= Mappers.getMapper(CurrencyMapper.class);

    public abstract CurrencyRequestDto mapRequestToRequestDto(CurrencyRequest request);

    public abstract List<CurrencyResponseDto> mapEntityListToResponseDtoList(List<Currency> entityList);

    public abstract CurrencyResponse mapResponseDtoToResponse(CurrencyResponseDto responseDto);

    public abstract List<CurrencyResponse> mapResponseDtoListToResponseList(List<CurrencyResponseDto> responseDtoList);
}
