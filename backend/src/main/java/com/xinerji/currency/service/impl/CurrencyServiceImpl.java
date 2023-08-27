package com.xinerji.currency.service.impl;

import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.constant.ExternalServiceEndPointConstant;
import com.xinerji.currency.constant.MessageKey;
import com.xinerji.currency.exceptions.XinerjiException;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.currency.Currency;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.entity.Role;
import com.xinerji.currency.model.entity.Users;
import com.xinerji.currency.model.mapper.CurrencyMapper;
import com.xinerji.currency.model.mapper.LoginMapper;
import com.xinerji.currency.model.mapper.UserMapper;
import com.xinerji.currency.model.type.RoleEnum;
import com.xinerji.currency.repository.RoleRepository;
import com.xinerji.currency.repository.UserRepository;
import com.xinerji.currency.security.JWTGenerator;
import com.xinerji.currency.service.CurrencyService;
import com.xinerji.currency.service.RestTemplateService;
import com.xinerji.currency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.xinerji.currency.model.type.RoleEnum.ADMIN;
import static com.xinerji.currency.model.type.RoleEnum.USER;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyMapper currencyMapper= CurrencyMapper.INSTANCE;

    private final RestTemplateService restTemplateService;
    private final MessageSource messageSource;

    @Override
    public CurrencyResponseDto getCurrencyRateByDate(CurrencyRequestDto requestDto) {

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

        List<Currency> currencyList = restTemplateService.callCurrencyService( url.toString());
        CurrencyResponseDto responseDto = new CurrencyResponseDto();
        responseDto.setCurrencyList(currencyList);
        return responseDto;
    }

    private String zeroNumberCorrection(String number) {
        if (Integer.valueOf(number)<10) {
            number = "0" + number;
        }
        return number;
    }
}
