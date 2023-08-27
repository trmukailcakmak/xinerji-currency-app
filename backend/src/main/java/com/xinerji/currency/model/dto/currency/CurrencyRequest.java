package com.xinerji.currency.model.dto.currency;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CurrencyRequest {

    private LocalDateTime date;
}
