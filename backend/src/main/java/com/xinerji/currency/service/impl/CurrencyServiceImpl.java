package com.xinerji.currency.service.impl;

import com.xinerji.currency.constant.ExternalServiceEndPointConstant;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.mapper.CurrencyMapper;
import com.xinerji.currency.service.CurrencyService;
import com.xinerji.currency.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyMapper currencyMapper= CurrencyMapper.INSTANCE;

    private final RestTemplateService restTemplateService;

    @Override
    public List<CurrencyResponseDto> getCurrencyRateByDate(CurrencyRequestDto requestDto) {

        List<CurrencyResponseDto> responseDtoList = restTemplateService.callCurrencyServiceByDate( requestDto.getDate());
        return responseDtoList;
    }
}
