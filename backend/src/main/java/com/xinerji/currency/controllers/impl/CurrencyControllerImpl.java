package com.xinerji.currency.controllers.impl;

import com.xinerji.currency.controllers.CurrencyController;
import com.xinerji.currency.model.dto.currency.CurrencyRequest;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponse;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.mapper.CurrencyMapper;
import com.xinerji.currency.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyControllerImpl implements CurrencyController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyControllerImpl.class);
    private final CurrencyMapper currencyMapper = CurrencyMapper.INSTANCE;
    private final CurrencyService currencyService;

    public CurrencyControllerImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @Override
    public ResponseEntity<List<CurrencyResponse>> getCurrencyRateByDate(CurrencyRequest request) {
        CurrencyRequestDto requestDto = currencyMapper.mapRequestToRequestDto(request);
        List<CurrencyResponseDto> responseDtoList = currencyService.getCurrencyRateByDate(requestDto);
        List<CurrencyResponse> responseList = currencyMapper.mapResponseDtoListToResponseList(responseDtoList);
        return ResponseEntity.ok(responseList);
    }
}
