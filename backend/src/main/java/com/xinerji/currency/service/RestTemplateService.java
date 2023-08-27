package com.xinerji.currency.service;

import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RestTemplateService {
    List<CurrencyResponseDto> callCurrencyServiceByDate(LocalDateTime dateTime);
}
