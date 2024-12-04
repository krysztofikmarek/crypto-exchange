package com.skiepko.cryptocurrency_exchange.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ToString
public class CoinGateRateDto {

    private Map<String, BigDecimal> rates;

    public CoinGateRateDto() {
        this.rates = new HashMap<>();
    }

    @JsonAnyGetter
    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    @JsonAnySetter
    public void setRates(String key, BigDecimal value) {
        rates.put(key, value);
    }
}
