package com.xinerji.currency.model.dto.currency;

import lombok.Data;

@Data
public class CurrencyResponseDto {

    private String isim;

    private String currencyName;

    private String forexBuying;

    private String forexSelling;

    private String banknoteBuying;

    private String banknoteSelling;

    private String crossRateUSD;

    private String crossRateOther;
}
