package com.xinerji.currency.service;


import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyResponseDto> getCurrencyRateByDate(CurrencyRequestDto requestDto);
}
