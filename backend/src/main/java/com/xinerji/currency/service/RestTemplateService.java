package com.xinerji.currency.service;


import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.currency.Currency;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponseDto;

import java.util.List;
import java.util.Objects;

public interface RestTemplateService {
    List<Currency> callCurrencyService(String url);
}
