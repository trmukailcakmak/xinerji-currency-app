package com.xinerji.currency.service;


import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponseDto;

public interface CurrencyService {
    CurrencyResponseDto getCurrencyRateByDate(CurrencyRequestDto requestDto);
}
