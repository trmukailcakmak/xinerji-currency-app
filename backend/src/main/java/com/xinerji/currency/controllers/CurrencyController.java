package com.xinerji.currency.controllers;

import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.model.dto.currency.CurrencyRequest;
import com.xinerji.currency.model.dto.currency.CurrencyResponse;
import com.xinerji.currency.model.dto.user.UserResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = EndPointConstant.CURRENCY_CONTROLLER, produces={MediaType.APPLICATION_JSON_VALUE})
public interface CurrencyController {

    @GetMapping(value = EndPointConstant.GET_CURRENCY_RATE_BY_DATE)
    @ApiOperation("get currency rate by date")
    ResponseEntity<List<CurrencyResponse>> getCurrencyRateByDate(@RequestBody CurrencyRequest request);

}
