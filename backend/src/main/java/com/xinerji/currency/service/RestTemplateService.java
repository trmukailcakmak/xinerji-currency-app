package com.xinerji.currency.service;

import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;

import java.util.List;

public interface RestTemplateService {
    List<CurrencyResponseDto> callCurrencyService(String url);
}
