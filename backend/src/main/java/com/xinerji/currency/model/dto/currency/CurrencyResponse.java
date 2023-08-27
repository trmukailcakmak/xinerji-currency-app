package com.xinerji.currency.model.dto.currency;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CurrencyResponse {

    private List<Currency> currencyList = new ArrayList<>();
}
