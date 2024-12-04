package com.skiepko.cryptocurrency_exchange.dto;

import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ExchangeDto {

    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal result;
    private BigDecimal fee;

    public static ExchangeDto fromExchangeResult(ExchangeResult result) {
        return ExchangeDto.builder()
                .rate(result.getRate())
                .amount(result.getAmount())
                .result(result.getResult())
                .fee(result.getFee())
                .build();
    }
}
