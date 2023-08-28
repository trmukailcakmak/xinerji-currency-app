package com.xinerji.currency.model.dto.currency;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CurrencyResponse {

    private String isim;

    private String currencyName;

    private String forexBuying;

    private String forexSelling;

    private String banknoteBuying;

    private String banknoteSelling;

    private String crossRateUSD;

    private String crossRateOther;

    private String currencyCode;
}
