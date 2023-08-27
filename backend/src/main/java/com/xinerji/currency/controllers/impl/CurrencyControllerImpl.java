package com.xinerji.currency.controllers.impl;

import com.xinerji.currency.controllers.CurrencyController;
import com.xinerji.currency.controllers.UserController;
import com.xinerji.currency.model.dto.currency.CurrencyRequest;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponse;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.mapper.CurrencyMapper;
import com.xinerji.currency.model.mapper.UserMapper;
import com.xinerji.currency.service.CurrencyService;
import com.xinerji.currency.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyControllerImpl implements CurrencyController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyControllerImpl.class);
    private final CurrencyMapper currencyMapper = CurrencyMapper.INSTANCE;
    private final CurrencyService currencyService;

    public CurrencyControllerImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @Override
    public ResponseEntity<CurrencyResponse> getCurrencyRateByDate(CurrencyRequest request) {
        CurrencyRequestDto requestDto = currencyMapper.mapRequestToRequestDto(request);
        CurrencyResponseDto responseDto = currencyService.getCurrencyRateByDate(requestDto);
        CurrencyResponse response = currencyMapper.mapResponseDtoToResponse(responseDto);
        return ResponseEntity.ok(response);
    }
}
