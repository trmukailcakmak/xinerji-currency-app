package com.xinerji.currency.model.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Currency {
    private String isim;
    private String currencyName;
    private String forexBuying;
    private String forexSelling;
    private String banknoteBuying;
    private String banknoteSelling;
    private String crossRateUSD;
    private String crossRateOther;
}
