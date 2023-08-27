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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyMapper currencyMapper= CurrencyMapper.INSTANCE;

    private final RestTemplateService restTemplateService;
    private final MessageSource messageSource;

    @Override
    public List<CurrencyResponseDto> getCurrencyRateByDate(CurrencyRequestDto requestDto) {

        String year = String.valueOf(requestDto.getDate().getYear());
        String month = String.valueOf(requestDto.getDate().getMonthValue());
        String day = String.valueOf(requestDto.getDate().getDayOfMonth());
        month = zeroNumberCorrection(month);
        day = zeroNumberCorrection(day);

        StringBuilder url = new StringBuilder();
        url.append(ExternalServiceEndPointConstant.GET_CURRENCY_RATE_SERVICE_URL);
        url.append(year);
        url.append(month);
        url.append("/");
        url.append(day);
        url.append(month);
        url.append(year);
        url.append(".xml");

        List<CurrencyResponseDto> responseDtoList = restTemplateService.callCurrencyService( url.toString());
        return responseDtoList;
    }

    private String zeroNumberCorrection(String number) {
        if (Integer.valueOf(number)<10) {
            number = "0" + number;
        }
        return number;
    }
}
