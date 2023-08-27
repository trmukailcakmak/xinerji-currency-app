package com.xinerji.currency.model.dto.currency;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CurrencyResponseDto {

    private List<Currency> currencyList = new ArrayList<>();
}
